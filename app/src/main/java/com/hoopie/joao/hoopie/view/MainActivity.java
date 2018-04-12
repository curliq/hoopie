package com.hoopie.joao.hoopie.view;

import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hoopie.joao.hoopie.R;
import com.hoopie.joao.hoopie.model.ActivitiesResponseEvent;
import com.hoopie.joao.hoopie.model.ActivityPOJO;
import com.hoopie.joao.hoopie.model.MainModel;
import com.hoopie.joao.hoopie.utils.Helper;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.mainActivity_tv_title)
    protected TextView tvTitle;
    @BindView(R.id.mainActivity_rv_activities)
    protected RecyclerView rvActivities;

    private ActivitiesRecyclerAdapter activitiesRecyclerAdapter;
    private ArrayList<ActivityPOJO> activitiesArray;
    private Helper helper = new Helper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        setupActivitiesRecyclerView();

        // get cached array of activities
        ArrayList<ActivityPOJO> cachedArray = new Gson().fromJson(
                helper.getPrefs(this).getString(getString(R.string.activitiesArray), null),
                new TypeToken<List<ActivityPOJO>>() {}.getType());

        // populate list with cached array of activities, if exists
        if (cachedArray != null)
            populateActivitiesList(cachedArray);

        // hit activities endpoint
        new MainModel().requestActivities(this);
    }

    /**
     * Initiate every generic thing about this activity that needs to be initiated.
     */
    private void init() {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/Happy-Marker.ttf");
        tvTitle.setTypeface(tf);
    }

    /**
     * Initiate the activities recycler adapter.
     */
    private void setupActivitiesRecyclerView() {
        activitiesArray = new ArrayList<>();
        activitiesRecyclerAdapter = new ActivitiesRecyclerAdapter(this, activitiesArray);
        rvActivities.setAdapter(activitiesRecyclerAdapter);
        rvActivities.setLayoutManager(new LinearLayoutManager(this));
        rvActivities.setItemAnimator(new DefaultItemAnimator());
    }

    /**
     * Pass a list of activities and compare it against the cached list of activities, if there's a new one then add it
     * This assumes that an activity will never have its data changed
     */
    private void populateActivitiesList(ArrayList<ActivityPOJO> activitiesArray) {
        for (ActivityPOJO activityPOJO : activitiesArray) {

            // Use Stream if api >= 24
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                if (!this.activitiesArray.stream().map(ActivityPOJO::getId)
                        .collect(Collectors.toCollection(ArrayList::new)).contains(activityPOJO.getId())) {

                    this.activitiesArray.add(activityPOJO);
                    this.activitiesRecyclerAdapter.notifyItemInserted(this.activitiesArray.indexOf(activityPOJO));
                }
            }
            else {
                for (ActivityPOJO cachedActivityPOJO : this.activitiesArray) {
                    if (!cachedActivityPOJO.getId().equals(activityPOJO.getId())) {
                        this.activitiesArray.add(activityPOJO);
                        this.activitiesRecyclerAdapter.notifyItemInserted(this.activitiesArray.indexOf(activityPOJO));
                    }
                }
            }
        }

        // sort the activities by start datetime
        Collections.sort(this.activitiesArray, (o1, o2) -> o1.getStartDate().compareTo(o2.getStartDate()));
    }

    /**
     * Listen for ActivitiesResponseEvent event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onActivitiesResponse(ActivitiesResponseEvent event) {
        populateActivitiesList(event.activitiesArray);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }
}

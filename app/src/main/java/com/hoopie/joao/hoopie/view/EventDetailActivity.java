package com.hoopie.joao.hoopie.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.hoopie.joao.hoopie.R;
import com.hoopie.joao.hoopie.model.ActivityPOJO;
import com.hoopie.joao.hoopie.utils.Helper;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EventDetailActivity extends AppCompatActivity {

    @BindView(R.id.eventDetailActivity_rl_backbutton)
    RelativeLayout rlBackButton;
    @BindView(R.id.eventDetailActivity_iv_thumbnail)
    ImageView ivThumbnail;
    @BindView(R.id.eventDetailActivity_tv_title)
    TextView tvTitle;
    @BindView(R.id.eventDetailActivity_tv_subTitle)
    TextView tvSubTitle;
    @BindView(R.id.eventDetailActivity_tv_tag)
    TextView tvTag;
    @BindView(R.id.eventDetailActivity_tv_description)
    TextView tvDescription;
    @BindView(R.id.eventDetailActivity_liv_day)
    ListItemView livDay;
    @BindView(R.id.eventDetailActivity_liv_hour)
    ListItemView livHour;
    @BindView(R.id.eventDetailActivity_liv_age)
    ListItemView livAge;
    @BindView(R.id.eventDetailActivity_liv_location)
    ListItemView livLocation;
    @BindView(R.id.eventDetailActivity_iv_map)
    ImageView ivMap;
    @BindView(R.id.eventDetailActivity_rl_action)
    RelativeLayout rlAction;

    private ActivityPOJO activityPOJO;
    private Helper helper = new Helper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        setActivityInfo();
    }

    private void init() {
        setContentView(R.layout.activity_event_detail);
        ButterKnife.bind(this);
        activityPOJO = new Gson().fromJson(getIntent().getStringExtra("activityPojo"), ActivityPOJO.class);
        rlBackButton.setOnClickListener(v -> onBackPressed());
    }

    private void setActivityInfo() {
        Picasso.get().load(activityPOJO.getImageURL()).into(ivThumbnail);
        tvTitle.setText(activityPOJO.getName());
        tvSubTitle.setText(activityPOJO.getPlaceName());
        tvTag.setText(activityPOJO.getCategory());
        tvDescription.setText(activityPOJO.getDescription());
        livDay.setIcon(R.drawable.ic_calendar);
        livDay.setText(helper.makeDateString(activityPOJO.getStartDate()));
        livHour.setIcon(R.drawable.ic_clock);
        livHour.setText(helper.makeHourString(activityPOJO.getStartDate(), activityPOJO.getEndDate()));
        livAge.setIcon(R.drawable.ic_baby);
        livAge.setText(helper.makeAgeString(activityPOJO.getAges()));
        livLocation.setIcon(R.drawable.ic_pin_drop);
        livLocation.setText(helper.makeLocationString(activityPOJO.getPlaceName(),
                activityPOJO.getAddress().getStreetName(), activityPOJO.getAddress().getPostcode()));
        setMap();
        rlAction.setOnClickListener(v ->
                Toast.makeText(EventDetailActivity.this, "not actually teleporting", Toast.LENGTH_SHORT).show()
        );
        new Handler().postDelayed(() -> rlBackButton.setVisibility(View.VISIBLE), 500);
    }

    private void setMap() {
        // listen for when the view gets drawn so we can get its dimensions
        ivMap.getViewTreeObserver().addOnGlobalLayoutListener(() ->
                Picasso.get().load(helper.getGoogleMapsImageUrl(
                        activityPOJO.getAddress().getLatitude(),
                        activityPOJO.getAddress().getLongitude(),
                        ivMap.getWidth(),
                        ivMap.getHeight())).into(ivMap)
        );

        ivMap.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(
                    "geo:" + activityPOJO.getAddress().getLatitude() + "," + activityPOJO.getAddress().getLongitude() +
                            "?q=" + activityPOJO.getAddress().getLatitude() + "," +
                            activityPOJO.getAddress().getLongitude() +
                            "(" + activityPOJO.getAddress().getPostcode() + " " +
                            activityPOJO.getAddress().getStreetName() + ")"));
            startActivity(intent);
        });

    }
}

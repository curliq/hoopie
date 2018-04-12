package com.hoopie.joao.hoopie.view;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.hoopie.joao.hoopie.R;
import com.hoopie.joao.hoopie.model.ActivityPOJO;
import com.hoopie.joao.hoopie.utils.Helper;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class ActivitiesRecyclerAdapter extends RecyclerView.Adapter<ActivitiesRecyclerAdapter.ViewHolder> {

    private ArrayList<ActivityPOJO> activitiesArray;
    private Helper helper = new Helper();
    private Activity activity;

    ActivitiesRecyclerAdapter(Activity activity, ArrayList<ActivityPOJO> activitiesArray) {
        this.activity = activity;
        this.activitiesArray = activitiesArray;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View jobItemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_activities_list, parent, false);
        return new ViewHolder(jobItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ActivityPOJO item = activitiesArray.get(position);

        Picasso.get().load(activitiesArray.get(position).getImageURL()).into(holder.ivThumbnail);
        holder.tvTitle.setText(item.getName());
        holder.tvSubTitle.setText(item.getPlaceName());
        holder.livDay.setIcon(R.drawable.ic_calendar);
        holder.livDay.setText(helper.makeDateString(item.getStartDate()));
        holder.livHour.setIcon(R.drawable.ic_clock);
        holder.livHour.setText(helper.makeHourString(item.getStartDate(), item.getEndDate()));
        holder.livAge.setIcon(R.drawable.ic_baby);
        holder.livAge.setText(helper.makeAgeString(item.getAges()));

        holder.rlClickableLayout.setOnClickListener(v -> {
            Intent intent = new Intent(activity, EventDetailActivity.class);
            Pair<View, String>[] pairs = new Pair[] {
                    new Pair<>(holder.ivThumbnail, activity.getString(R.string.iv_thumbnail_transition)),
                    new Pair<>(holder.llShadowLayout, activity.getString(R.string.ll_thumbnail_shadow_transition)),
                    new Pair<>(holder.tvTitle, activity.getString(R.string.tv_title_transition)),
                    new Pair<>(holder.tvSubTitle, activity.getString(R.string.tv_subtitle_transition))
            };
            ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, pairs);
            intent.putExtra("activityPojo", new Gson().toJson(item));
            activity.startActivity(intent, options.toBundle());

        });
    }

    @Override
    public int getItemCount() {
        return activitiesArray.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout rlClickableLayout;
        LinearLayout llShadowLayout;
        TextView tvTitle, tvSubTitle;
        ImageView ivThumbnail;
        ListItemView livDay, livHour, livAge;

        ViewHolder(View itemView) {
            super(itemView);

            rlClickableLayout = itemView.findViewById(R.id.item_activities_rl_clickable_layout);
            llShadowLayout = itemView.findViewById(R.id.item_activities_ll_shadow);
            tvTitle = itemView.findViewById(R.id.item_activities_tv_title);
            tvSubTitle = itemView.findViewById(R.id.item_activities_tv_subTitle);
            ivThumbnail = itemView.findViewById(R.id.item_activities_iv_thumbnail);
            livDay = itemView.findViewById(R.id.item_activities_liv_day);
            livHour = itemView.findViewById(R.id.item_activities_liv_hour);
            livAge = itemView.findViewById(R.id.item_activities_liv_age);
        }
    }
}

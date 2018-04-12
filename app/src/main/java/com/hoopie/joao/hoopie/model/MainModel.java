package com.hoopie.joao.hoopie.model;

import com.hoopie.joao.hoopie.utils.Helper;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * John was here on 12/04/2018, exactly by 01:53
 */
public class MainModel {

    /**
     * hit the activities endpoint and fire an event on response
     */
    public void requestActivities() {
        new Helper().makeRetrofit().create(HoopAPI.class).getActivities()
                .enqueue(new Callback<ArrayList<ActivityPOJO>>() {
                    @Override
                    public void onResponse(Call<ArrayList<ActivityPOJO>> call,
                                           Response<ArrayList<ActivityPOJO>> response) {

                        int status = response.isSuccessful() ? Helper.HttpResponses.SUCCESS.ordinal()
                                                             : Helper.HttpResponses.SERVER_ERROR.ordinal();

                        EventBus.getDefault().post(new ActivitiesResponseEvent(status, response.body()));
                    }

                    @Override
                    public void onFailure(Call<ArrayList<ActivityPOJO>> call, Throwable t) {
                        EventBus.getDefault().post(new ActivitiesResponseEvent(
                                Helper.HttpResponses.NETWORK_ERROR.ordinal(), null));
                    }
                });
    }

}

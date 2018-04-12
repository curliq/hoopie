package com.hoopie.joao.hoopie.model;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface HoopAPI {

    @GET("test.json")
    Call<ArrayList<ActivityPOJO>> getActivities();

}

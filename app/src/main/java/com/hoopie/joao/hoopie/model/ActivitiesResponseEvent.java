package com.hoopie.joao.hoopie.model;

import java.util.ArrayList;

/**
 * Object that gets sent through on the event of a response from the activities endpoint.
 */
public class ActivitiesResponseEvent {

    public int status;
    public ArrayList<ActivityPOJO> activitiesArray;

    /**
     * Pass the activities array and a status so we know what kind of error happened or if it was successful
     */
    public ActivitiesResponseEvent(int status, ArrayList<ActivityPOJO> activitiesArray) {
        this.status = status;
        this.activitiesArray = activitiesArray;
    }

}

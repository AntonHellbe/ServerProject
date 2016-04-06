package org.demo.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import com.mongodb.util.JSON;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Anton on 2016-04-06.
 */

public class TimeSamples implements Serializable{

    private User currentUser;
    private String userId;
    private Date date;


    public TimeSamples() {

    }
    public String newTimeSample() {
        Date date = new Date();
        this.userId = currentUser.getId();
    }

    public String getUserId() {
        return userId;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @JsonSerialize(using = DateSerializer.class)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getCurrentUser() {
        return currentUser;
    }






}

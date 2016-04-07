package org.demo.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import com.mongodb.util.JSON;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Anton on 2016-04-06.
 */

public class TimeSamples implements Serializable{

    private boolean inCheckning;
    private Date date;



    public TimeSamples() {

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        this.date = new Date();

        Calendar cal = Calendar.getInstance();
    }
    public Date newTimeSample() {
        return date;
    }

    @JsonSerialize(using = DateSerializer.class)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String toString() {
        return this.date.toString();
    }







}

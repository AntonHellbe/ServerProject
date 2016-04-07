package org.demo.model;

import java.util.Calendar;
import org.springframework.data.annotation.Id;

/**
 * Created by Anton on 2016-04-07.
 */
public class Stamp {
    private Calendar date;
    private boolean checkIn;


    public Stamp(Calendar date, boolean checkIn) {
        this.date = date;
        this.checkIn = checkIn;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public boolean isCheckIn() {
        return checkIn;
    }

    public void setCheckIn(boolean checkIn) {
        this.checkIn = checkIn;
    }

    //@Id
    //private String id;


}

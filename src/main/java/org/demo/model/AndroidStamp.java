package org.demo.model;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by Anton on 2016-04-07.
 */
public class AndroidStamp extends Stamp implements Serializable{

    public AndroidStamp(Calendar date, boolean checkIn) {
        super(date, checkIn);
    }

    public void setDate(Calendar date) {
        super.setDate(date);
    }

    public Calendar getDate() {
        return super.getDate();
    }

    public void setCheckIn(boolean checkIn) {
        super.setCheckIn(checkIn);
    }

    public boolean getCheckIn() {
        return super.isCheckIn();
    }



}

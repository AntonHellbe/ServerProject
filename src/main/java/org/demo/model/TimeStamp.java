package org.demo.model;

import java.util.Calendar;

/**
 * Created by Anton on 2016-04-07.
 */
public class TimeStamp extends Stamp {


    private RfidKey rfidkey;

    public TimeStamp(Calendar date, boolean checkIn, RfidKey rfidkey) {
        super(date, checkIn);
        this.rfidkey = rfidkey;
    }

    public RfidKey getRfidkey() {
        return rfidkey;
    }

    public void setRfidkey(RfidKey rfidkey) {
        this.rfidkey = rfidkey;
    }

    public Calendar getDate() {
        return super.getDate();
    }

    public void setDate(Calendar date) {
        super.setDate(date);
    }

    public boolean getCheckIn() {
        return super.isCheckIn();
    }

    public void setCheckIn(boolean checkIn){
        super.setCheckIn(checkIn);
    }


}

package org.demo.model;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by Anton on 2016-04-07.
 */

/**
 * AndroidStamp is the Stamp that is used by the android clients,
 * and therefor is whats sent to the Android units
 **/
public class AndroidStamp extends Stamp implements Serializable{

    /**
     * Constructor which creates a new androidStamp
     **/
    public AndroidStamp(Calendar date, boolean checkIn) {
        super(date, checkIn);
    }

	public AndroidStamp(TimeStamp timeStamp) {
		this.date = timeStamp.getDate();
		this.checkIn = timeStamp.getCheckIn();
	}


	/**
     * Sets the date of the Stamp
     * @param date the date to be set to the stamp
     **/
    public void setDate(Calendar date) {
        super.setDate(date);
    }

    /**
     * Fetches the date of the Stamp
     * @return the date of the stamp
     **/
    public Calendar getDate() {
        return super.getDate();
    }

    /**
     *Sets the checkIn og the stamp to true/false
     * @param checkIn if it should be set to true or false
     **/
    public void setCheckIn(boolean checkIn) {
        super.setCheckIn(checkIn);
    }

    /**
     * Fetches the checkIn status of the Stamp
     * @return if its true/false
     **/
    public boolean getCheckIn() {
        return super.isCheckIn();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}

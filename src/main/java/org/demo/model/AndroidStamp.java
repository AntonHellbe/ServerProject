package org.demo.model;

import java.io.Serializable;

/**
 * @Author Anton Hellbe
 */

/**
 * AndroidStamp is the Stamp that is used by the android clients,
 * and therefor is whats sent to the Android units
 **/
public class AndroidStamp extends Stamp implements Serializable{

    /**
     * Constructor which creates a new androidStamp
     **/
    public AndroidStamp(long date, boolean checkIn) {
        this.date = date;
        this.checkIn = checkIn;
    }

	public AndroidStamp(TimeStamp timeStamp) {
		this.date = timeStamp.getDate();
		this.checkIn = timeStamp.getCheckIn();
	}

	/**
     * Sets the date of the Stamp

     **/
    public void setDate(long date) {
        this.date = date;
    }

    /**
     * Fetches the date of the Stamp
     * @return the date of the stamp
     **/
    public long getDate() {
        return this.date;
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

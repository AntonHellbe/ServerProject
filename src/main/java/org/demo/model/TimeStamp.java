package org.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.io.Serializable;
import java.util.Calendar;


/**
 * Created by Anton on 2016-04-07.
 */

/**
 * TimeStamp used by the Server
 **/
@Document(collection = "timestamps")
public class TimeStamp extends Stamp implements Serializable {

    @Id
    private String id;


    private RfidKey rfidKey;

    /**
     * Formats the information in the TimeStamp to a String
     * @return the formatted information
     **/


    /**
     *Constructor that creates a new TimeStamp with given data
     **/
    public TimeStamp() {

    }

    public TimeStamp(Calendar date, boolean checkIn, RfidKey rfidKey) {
        super(date, checkIn);
        this.rfidKey = rfidKey;
    }

    /**
     * Fetches the id of the TimeStamp
     * @return the id of the TimeStamp
     **/
	public String getId() {
		return this.id;
	}

    /**
     * Sets the id of the TimeStamp
     * @param id the new id
     **/
	public void setId(String id) {
		this.id = id;
	}

    /**
     *Fetches the RFID-key of the TimeStamp
     * @return the RFID-key
     **/

    /**
     * Sets a new RFID-key to the TimeStamp
     * @param rfidkey the new RFID-key
     **/

    /**
     * Fetches the date of the TimeStamp
     * @return the date
     **/
    public Calendar getDate() {
        return super.getDate();
    }

    /**
     * Sets a new date to the TimeStamp
     * @param date the new date
     **/
    public void setDate(Calendar date) {
        super.setDate(date);
    }

    /**
     * Fetches the checkIn status of the TimeStamp
     * @return the status
     **/
    public boolean getCheckIn() {
        return super.isCheckIn();
    }

    /**
     * Sets the status of the TimeStamp
     * @param checkIn the new Status
     **/
    public void setCheckIn(boolean checkIn){
        super.setCheckIn(checkIn);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TimeStamp timeStamp = (TimeStamp) o;

        return !(id != null ? !id.equals(timeStamp.id) : timeStamp.id != null);

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "TimeStamp{" +
                "id='" + this.id + '\'' +
                ", rfidKey=" + rfidKey +
                super.toString() + '}';
    }
}

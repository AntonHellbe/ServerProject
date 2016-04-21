package org.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Calendar;

/**
 * Created by Anton on 2016-04-07.
 */

/**
 * TimeStamp used by the Server
 **/
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "TimeStamps")
public class TimeStamp extends Stamp {

	@Id
	private String id;

    private RfidKey rfidKey;



    /**
     *Constructor that creates a new Timestamp
     **/
    public TimeStamp() {
	    super();
    }

    /**
     *Constructor that creates a new TimeStamp with given data
     * @param date the date and time of the timeStamp
     * @param checkIn the state of the TimeStamp(true/false)
     * @param rfidKey The Rfid key that the Timestamp is associated with
     **/
    public TimeStamp(Calendar date, boolean checkIn, RfidKey rfidKey) {
        super(date, checkIn);
        this.rfidKey = rfidKey;
    }

    /**
     * Fetches the id of the TimeStamp
     * @return the id of the TimeStamp
     **/
	public String getId() {
		return id;
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
	public RfidKey getRfidkey() {
        return rfidKey;
    }

    /**
     * Sets a new RFID-key to the TimeStamp
     * @param rfidkey the new RFID-key
     **/
    public void setRfidkey(RfidKey rfidkey) {
        this.rfidKey = rfidkey;
    }

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

	/**
	 * Formats the information in the TimeStamp to a String
	 * @return the formatted information
	 **/
	@Override
	public String toString() {
		return "TimeStamp{" +
				"ID=" + id +"\n"+
				"rfid=" + rfidKey +"\n"+
				super.toString()+
				'}';
	}

}

package org.demo.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.CalendarSerializer;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import org.springframework.data.annotation.Id;

/**
 * Created by Anton on 2016-04-07.
 */

/**
 * Class that acts as the superclass of the other Stamps.
 * Contains times and other information about the Stamp
 **/
public class Stamp implements Serializable{

	@JsonSerialize(using = CalendarSerializer.class)
	protected Calendar date;

    //True = inCheckning
    //False = utCheckning
    protected boolean checkIn;

    /**
     *Empty constructor, needed for the db
     **/
    public Stamp() {
    }

    /**
     *Constructor that creates a new stamp
     * @param checkIn sets the checkIn status to true/false
     * @param date sets the date
     **/
    public Stamp(Calendar date, boolean checkIn) {
        this.date = date;
        this.checkIn = checkIn;
    }

    /**
     *get the date of the Stamp
     * @return the date
     **/
    public Calendar getDate() {
        return date;
    }

    /**
     *Sets the date of the Stamp
     * @param date the new date
     **/
    public void setDate(Calendar date) {
        this.date = date;
    }

    /**
     * checks if the Stamp is true/fasle
     * @return true/false
     **/
    public boolean isCheckIn() {
        return checkIn;
    }

    /**
     *Sets the cehckIn status of the Stamp to true/false
     * @param checkIn the new status of the Stamp
     **/
    public void setCheckIn(boolean checkIn) {
        this.checkIn = checkIn;
    }

    /**
     * Formats the information of the Stamp to a String
     * @return the formatted information
     **/

    @Override
    public String toString() {
        SimpleDateFormat date_format = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        return "Stamp{" +
                "date=" + date_format.format(date.getTime()) +
                ", checkIn=" + checkIn +
                '}';
    }
}

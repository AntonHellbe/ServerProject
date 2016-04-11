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
public class Stamp implements Serializable{

	@JsonSerialize(using = CalendarSerializer.class)
	protected Calendar date;
    protected boolean checkIn;

    public Stamp() {
    }

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

	@Override
	public String toString() {
		SimpleDateFormat date_format = new SimpleDateFormat("yyyy-MM-dd HH:mm");

		return "Stamp{" +
				"date=" + date_format.format(date.getTime()) +
				", checkIn=" + checkIn +
				'}';
	}

	//@Id
    //private String id;


}

package org.demo.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.springframework.data.annotation.Id;

/**
 * Created by Anton on 2016-04-07.
 */
public class Stamp {
    private Calendar date;
    private boolean checkIn;

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

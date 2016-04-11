package org.demo.model;

import org.springframework.data.annotation.Id;

import java.util.Calendar;

/**
 * Created by Anton on 2016-04-07.
 */
public class TimeStamp extends Stamp {

	@Id
	private int id;

	static int secretId= 0;

    private RfidKey rfidKey;

    @Override
	public String toString() {
		return "TimeStamp{" +
				"ID=" + id +"\n"+
				"rfidKey=" + rfidKey +"\n"+
				super.toString()+
				'}';
	}

	public TimeStamp() {
        super();
		this.id = secretId;
		secretId++;

    }

    public TimeStamp(Calendar date, boolean checkIn, RfidKey rfidkey) {
        super(date, checkIn);
        this.rfidKey = rfidkey;
	    this.id = secretId;
	    secretId++;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public RfidKey getRfidkey() {
        return rfidKey;
    }

    public void setRfidkey(RfidKey rfidkey) {
        this.rfidKey = rfidkey;
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

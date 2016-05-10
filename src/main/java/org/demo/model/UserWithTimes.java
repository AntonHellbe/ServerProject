//package org.demo.model;
//
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
//import com.fasterxml.jackson.databind.annotation.JsonSerialize;
//import com.fasterxml.jackson.databind.ser.std.CalendarSerializer;
//import org.demo.deserialize.RfidKeyDeserializer;
//
//import java.util.Calendar;
//
///**
// * Created by seb on 2016-05-09.
// */
//@JsonIgnoreProperties(ignoreUnknown = true)
//public class UserWithTimes {
//
//	private String userId;
//	private String username;
//	private String firstName;
//	private String lastName;
//
//	private String rfidKey;
//
//	@JsonSerialize(using = CalendarSerializer.class)
//	private Calendar date;
//
//	private boolean checkIn;
//
//	public UserWithTimes() {
//	}
//
//	public String getUserId() {
//		return userId;
//	}
//
//	public void setUserId(String userId) {
//		this.userId = userId;
//	}
//
//	public String getUsername() {
//		return username;
//	}
//
//	public void setUsername(String username) {
//		this.username = username;
//	}
//
//	public String getFirstName() {
//		return firstName;
//	}
//
//	public void setFirstName(String firstName) {
//		this.firstName = firstName;
//	}
//
//	public String getLastName() {
//		return lastName;
//	}
//
//	public void setLastName(String lastName) {
//		this.lastName = lastName;
//	}
//
//	public RfidKey getRfidKey() {
//		return rfidKey;
//	}
//
//	public void setRfidKey(RfidKey rfidKey) {
//		this.rfidKey = rfidKey;
//	}
//
//	public Calendar getDate() {
//		return date;
//	}
//
//	public void setDate(Calendar date) {
//		this.date = date;
//	}
//
//	public boolean isCheckIn() {
//		return checkIn;
//	}
//
//	public void setCheckIn(boolean checkIn) {
//		this.checkIn = checkIn;
//	}
//}

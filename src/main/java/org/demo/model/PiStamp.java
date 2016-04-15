package org.demo.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by seb on 2016-04-11.
 */

/***
 * Pistamp is used on the pi for dispalying info when a user stamps in.
 * this is the return model
 */

public class PiStamp extends Stamp implements Serializable {

	private final String lastName;
	private final String firstName;


	/**
	 * Create a new instance of a Pistamp
	 * @param checkIn
	 * @param user
	 */
	public PiStamp(boolean checkIn, User user) {
		this.checkIn = checkIn;
		this.date = Calendar.getInstance();
		this.firstName =user.getFirstName();
		this.lastName =user.getLastName();
	}

	/**
	 * Fetches the last name of the user
	 * @return the last name
	 **/
	public String getLastName() {
		return lastName;
	}

	/**
	 * Fetches the first name of th user
	 * @return the first name
	 **/
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Formats the information of the Stamp to a String
	 * @return the formated information
	 **/
	@Override
	public String toString() {
		return "PiStamp{" +
				"lastName='" + lastName + '\'' +
				", firstName='" + firstName + '\'' +
				", Time='" + this.getDate()+ '\'' +
				", CheckIn='" + this.isCheckIn()    + '\'' +
				'}';
	}
}
package org.demo.model;

import org.demo.model.security.Account;

import java.io.Serializable;
import java.util.Calendar;

/***
 * @author Sebastian Börebäck on 2016-04-11.
 * Pistamp is used on the pi for dispalying info when a user stamps in.
 * this is the return model
 */
public class PiStamp extends Stamp implements Serializable {



	private String lastName;
	private String firstName;


	/**
	 * Create a new instance of a Pistamp
	 * @param checkIn
	 * @param user
	 */



	public PiStamp(boolean checkIn, Account user) {
		this.date = Calendar.getInstance().getTimeInMillis();
		this.checkIn = checkIn;
		this.firstName =user.getFirstName();
		this.lastName =user.getLastName();
	}

	public PiStamp() {

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
				", Time='" + this.date + '\'' +
				", CheckIn='" + this.isCheckIn()    + '\'' +
				'}';
	}
}

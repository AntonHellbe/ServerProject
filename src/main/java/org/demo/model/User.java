package org.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Anton on 2016-04-06.
 */

/**
 * User class that contains the information about the users
 **/
@Document (collection = "users")
public class User implements Serializable{

	private String firstName;
	private String lastName;
	private RfidKey rfidKey;


	@Id
	private String id;


	/**
	 * Constructor that starts the creation of a user
	 **/
	public User() {
		//this.id = String.valueOf(secretId);
		//secretId++;
	}


	/**
	 * Constructor that creates a new user
	 * @param firstName The first name of the user
	 * @param lastName the last name of the user
	 * @param rfid the RFID-key of the user
	 **/

	public User(String firstName, String lastName, RfidKey rfid) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.rfidKey = rfid;
	}

	/**
	 *Checks if the sent in first-name is equal to to the users first-name
	 * @param name the first-name to be compared with the users
	 * @return true or false
	 **/
	public boolean checkIdentity(String name) {
		if (name.equals(this.firstName)) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if the sent in RFID is equal to to the users RFID
	 * @param rfid the rfid to be compared with the users
	 * @return true or false
	 **/
	public boolean checkRfid(String rfid) {
		if (this.rfidKey.equals(rfid)) {
			return true;
		}

		return false;
	}

	/**
	 * Fetches the first name of the user
	 * @return the first name
	 **/
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Fetches the last name of the user
	 * @return the last name
	 **/
	public String getLastName() {
		return lastName;
	}

	/**
	 *Fetches the id of the user
	 * @return the id
	 **/
	public String getId() {
		return id;
	}

	/**
	 * Fetches the RFID of the user
	 * @return the RFID
	 **/
	public RfidKey getRfid() {
		return rfidKey;
	}

	/**
	 * Sets a new first name of the user
	 * @param firstName the new first name
	 **/
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Sets a new last name for the user
	 * @param lastName the new last name
	 **/
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Sets a new RFID for the user
	 * @param rfid the new RFID
	 **/
	public void setRfid(RfidKey rfid) {
		this.rfidKey = rfid;
	}

	/**
	 *Sets a new id for the user
	 * @param id the new id
	 **/
	public void setId(String id) {
		this.id = id;
	}


	@Override
	public String toString() {
		return "User{" +
				"firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", rfid='" + rfidKey + '\'' +
				", id='" + id + '\'' +
				'}';
	}

}

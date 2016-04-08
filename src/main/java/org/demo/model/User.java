package org.demo.model;

import org.springframework.data.annotation.Id;

/**
 * Created by Anton on 2016-04-06.
 */

public class User {

	private String firstName;
	private String lastName;
	private RfidKey key;

	@Id
	private String id;

	static int secretId= 0;

	public User() {
		this.id = String.valueOf(secretId);
		secretId++;
	}


	public User(String firstName, String lastName, RfidKey rfid, String id) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.key = rfid;
		this.id = id;
//		try {
//			if (Integer.parseInt(id) > 0) {
//				secretId = Integer.parseInt(id);
//			}
//		} catch (NumberFormatException e) {
//			e.printStackTrace();
//		}
	}

	public static int getSecretId() {
		return secretId;
	}

	public static void setSecretId(int secretId) {
		User.secretId = secretId;
	}

	public boolean checkIdentity(String name) {
		if (name.equals(this.firstName)) {
			return true;
		}
		return false;
	}

	public boolean checkRfid(String rfid) {
		if (this.key.equals(rfid)) {
			return true;
		}

		return false;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getId() {
		return id;
	}

	public RfidKey getRfid() {
		return key;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setRfid(RfidKey rfid) {
		this.key = rfid;
	}

	public void setId(String id) {
		this.id = id;
	}


	@Override
	public String toString() {
		return "User{" +
				"firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", rfid='" + key + '\'' +
				", id='" + id + '\'' +
				'}';
	}
}

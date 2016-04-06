package org.demo.model;

import org.springframework.data.annotation.Id;

import java.util.ArrayList;

/**
 * Created by Anton on 2016-04-06.
 */

public class User {

	private String firstName;
	private String lastName;
	private String rfid;
	private ArrayList<TimeSamples> samples = new ArrayList<>();

	@Id
	private String id;

	static int secretId= 0;

	public User() {
		this.id = String.valueOf(secretId);
		secretId++;
	}

	public User(String firstName, String lastName, String rfid, String id) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.rfid = rfid;
		this.id = id;
		try {
			if (Integer.parseInt(id) > 0) {
				secretId = Integer.parseInt(id);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
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
		if (this.rfid.equals(rfid)) {
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

	public String getRfid() {
		return rfid;
	}

	public ArrayList<TimeSamples> getSamples() {
		return samples;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setRfid(String rfid) {
		this.rfid = rfid;
	}

	public void setSamples(ArrayList<TimeSamples> samples) {
		this.samples = samples;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void newSample() {
		samples.add(new TimeSamples());
	}

	@Override
	public String toString() {
		return "User{" +
				"firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", rfid='" + rfid + '\'' +
				", samples=" + samples +
				", id='" + id + '\'' +
				'}';
	}
}

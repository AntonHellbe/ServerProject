package org.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Sebastian Börebäck on 2016-04-21.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class AndroidBetweenQuery {

	RfidKey id;
	long from;
	long to;

	public AndroidBetweenQuery() {
	}

	public RfidKey getId() {
		return id;
	}

	public void setId(RfidKey id) {
		this.id = id;
	}

	public long getFrom() {
		return from;
	}

	public void setFrom(long from) {
		this.from = from;
	}

	public long getTo() {
		return to;
	}

	public void setTo(long to) {
		this.to = to;
	}

	@Override
	public String toString() {
		return "AndroidBetweenQuery{" +
				"key=" + id +
				", from=" + from +
				", to=" + to +
				'}';
	}
}

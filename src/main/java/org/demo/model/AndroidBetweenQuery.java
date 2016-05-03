package org.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.org.apache.xpath.internal.operations.And;

/**
 * Created by Sebastian Börebäck on 2016-04-21.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class AndroidBetweenQuery {

	RfidKey rfidKey;
	long from;
	long to;

	public AndroidBetweenQuery(long from, long to, RfidKey rfidKey){
		this.from = from;
		this.to = to;
		this.rfidKey = rfidKey;
	}

	public AndroidBetweenQuery() {
	}

	public RfidKey getId() {
		return rfidKey;
	}

	public void setId(RfidKey id) {
		this.rfidKey = id;
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
				"key=" + rfidKey +
				", from=" + from +
				", to=" + to +
				'}';
	}
}

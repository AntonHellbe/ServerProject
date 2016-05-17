package org.demo.model.ws;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Sebastian Börebäck on 2016-05-16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class WsMessage {

	AffectedArea area;
	CrudType crudType;
	String affectedId;

	private String name;

	public WsMessage() {
	}

	public WsMessage(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public WsMessage(AffectedArea area, CrudType crudType, String affectedId) {
		this.area = area;
		this.crudType = crudType;
		this.affectedId = affectedId;
	}

	public AffectedArea getArea() {
		return area;
	}

	public CrudType getCrudType() {
		return crudType;
	}

	public String getAffectedId() {
		return affectedId;
	}

	@Override
	public String toString() {
		return "WsMessage{" +
				"area=" + area +
				", crudType=" + crudType +
				", affectedId='" + affectedId + '\'' +
				", name='" + name + '\'' +
				'}';
	}
}

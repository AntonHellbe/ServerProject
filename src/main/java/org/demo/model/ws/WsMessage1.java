package org.demo.model.ws;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Sebastian Börebäck on 2016-05-16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class WsMessage1 {

	AffectedArea area;
	CrudType crudType;
	String affectedId;

	private String name;

	public WsMessage1() {
	}

	public WsMessage1(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public WsMessage1(AffectedArea area, CrudType crudType, String affectedId) {
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
		return "WsMessage1{" +
				"area=" + area +
				", crudType=" + crudType +
				", affectedId='" + affectedId + '\'' +
				", name='" + name + '\'' +
				'}';
	}
}

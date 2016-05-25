package org.demo.model.ws;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Sebastian Börebäck on 2016-05-16.
 * Websocket request from client model
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class WsMessage {

	AffectedArea area;
	CrudType crudType;
	String affectedId;
	String token;

	Object payload;

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

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Object getPayload() {
		return payload;
	}

	public void setPayload(Object payload) {
		this.payload = payload;
	}

	@Override
	public String toString() {
		return "WsMessage{" +
				"area=" + area +
				", crudType=" + crudType +
				", affectedId='" + affectedId + '\'' +
				", token='" + token + '\'' +
				", payload=" + payload +
				", name='" + name + '\'' +
				'}';
	}
}

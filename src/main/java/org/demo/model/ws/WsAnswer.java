package org.demo.model.ws;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Arrays;

/**
 * Created by Sebastian Börebäck on 2016-05-16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class WsAnswer {
	private String token;
	private String content;

	AffectedArea area;
	CrudType crudType;
	String affectedId;

	Object payload;
	Object[] payloadList;
	private String error;

	public WsAnswer() {
	}

	public WsAnswer(String content) {
		this.content = content;
	}

	public WsAnswer(AffectedArea area, CrudType crudType, String affectedId) {
		this.area = area;
		this.crudType = crudType;
		this.affectedId = affectedId;
	}

	public WsAnswer(WsMessage message) {
		this.crudType = message.getCrudType();
		this.area = message.getArea();
		this.affectedId = message.getAffectedId();
		this.token = message.getToken();
	}

	public AffectedArea getArea() {
		return area;
	}

	public void setArea(AffectedArea area) {
		this.area = area;
	}

	public CrudType getCrudType() {
		return crudType;
	}

	public void setCrudType(CrudType crudType) {
		this.crudType = crudType;
	}

	public String getAffectedId() {
		return affectedId;
	}

	public void setAffectedId(String affectedId) {
		this.affectedId = affectedId;
	}

	public String getContent() {
		return content;
	}

	public Object getPayload() {
		return payload;
	}

	public void setPayload(Object payload) {
		this.payload = payload;
	}

	public Object[] getPayloadList() {
		return payloadList;
	}

	public void setPayloadList(Object[] payloadList) {
		this.payloadList = payloadList;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "WsAnswer{" +
				"payloadList=" + Arrays.toString(payloadList) +
				", payload=" + payload +
				", affectedId='" + affectedId + '\'' +
				", crudType=" + crudType +
				", area=" + area +
				", token='" + token + '\'' +
				'}';
	}

	public void setError(String error) {
		this.error = error;
	}
}

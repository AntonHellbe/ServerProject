package org.demo.model.ws;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Sebastian Börebäck on 2016-05-16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class WsAnswer {
	private String content;

	AffectedArea area;
	CrudType crudType;
	String affectedId;

	Object payload;

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

	@Override
	public String toString() {
		return "WsAnswer{" +
				"content='" + content + '\'' +
				", area=" + area +
				", crudType=" + crudType +
				", affectedId='" + affectedId + '\'' +
				", payload=" + payload +
				'}';
	}
}

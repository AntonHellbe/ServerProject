package org.demo.model;

import org.springframework.data.annotation.Id;

import java.io.Serializable;

/**
 * Created by Sebastian Börebäck on 2016-04-08.
 */

public class Item implements Serializable{
	@Id
	private Integer id;

	private boolean checked;

	private String description;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Item{" +
				"id=" + id +
				", checked=" + checked +
				", description='" + description + '\'' +
				'}';
	}
}

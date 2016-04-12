package org.demo.example;

import org.springframework.data.annotation.Id;

import java.io.Serializable;

/**
 * Created by Sebastian Börebäck on 2016-04-08.
 */

/**
 * Item that is handled by other classes
 **/
public class Item implements Serializable{
	@Id
	private Integer id;

	private boolean checked;

	private String description;


	/**
	 * Fetches the id of the item
	 * @return the id if the item
	 **/
	public Integer getId() {
		return id;
	}

	/**
	 *Sets the id of the item to what we want it to be
	 * @param id the new id
	 **/
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Checks if the item is ... checked
	 * @return true/false dependingon what the state of the check is in
	 **/
	public boolean isChecked() {
		return checked;
	}

	/**
	 * Sets the status of the check to true/false
	 * @param checked the true/false value
	 **/
	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	/**
	 *Fetches the description of the item
	 * @return the Items description
	 **/
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description of the item
	 * @param description the new description
	 **/
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Returns the information in the Item to a String format
	 * @return the formated text
	 **/
	@Override
	public String toString() {
		return "Item{" +
				"id=" + id +
				", checked=" + checked +
				", description='" + description + '\'' +
				'}';
	}
}

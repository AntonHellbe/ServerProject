package org.demo.model;

import java.io.Serializable;

/**
 * Created by Anton on 2016-04-07.
 */

/**
 * A class that creates an RFID-key from input
 **/

public class RfidKey implements Serializable{

    private String id;

    /**
     * Constructor that creates a new RFID-key
     **/
    public RfidKey(String id) {
        this.id = id;
    }

    /**
     * Fetches the id of the RFID-key
     **/
    public String getId() {
        return id;
    }

    /**
     * Sets the id of the RFID-key
     * @param id the new id
     **/
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Formats the information of the Key to a String
     * @return the formatted information
     **/
    @Override
    public String toString() {
        return id;
    }

    /**
     * Checks if the RFID-Key is equal to another object
     * @param o the object to check if its equal
     * @return true or false
     **/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RfidKey rfidKey = (RfidKey) o;

        return id != null ? id.equals(rfidKey.id) : rfidKey.id == null;

    }

    /**
     * Returns the hashCode of the RFID-key if it exists
     * @return null or the hashCode
     **/
    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}

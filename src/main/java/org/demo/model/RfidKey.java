package org.demo.model;

import java.io.Serializable;

/**
 * Created by Anton on 2016-04-07.
 */

public class RfidKey implements Serializable{

    private String id;

    public RfidKey(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "RfidKey{" +
                "id='" + id + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RfidKey rfidKey = (RfidKey) o;

        return id != null ? id.equals(rfidKey.id) : rfidKey.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}

package org.demo.model;

/**
 * Created by Anton on 2016-04-07.
 */
public class RfidKey {

    private String id;

    public RfidKey(String part) {
        String id = part;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String toString() {
        return id;
    }
}

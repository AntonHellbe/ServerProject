package org.demo.service;

import org.demo.model.RfidKey;
import org.demo.model.TimeStamp;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Robin_2 on 11/04/2016.
 */

/**
 * Interface for methods used by the server to handle times
 **/
public interface TimeService {

    /**
     *Adds a new TimeStamp to the user
     * @param id the id of the user
     * @return the added TimeStamp
     **/
    public ResponseEntity<TimeStamp> addTime(String id);
    /**
     *Adds a new TimeStamp to the user
     * @param rfidKey the id of the user
     * @return the added TimeStamp
     **/

    /**
     * Deletes the specified TimeStamp from the given user
     * @param id id of the user
     * @param stampId the TimeStamp to be deleted
     * @return the deleted time
     **/
    public ResponseEntity<TimeStamp> deleteTime(String id, String stampId);
    /**
     * Deletes the specified TimeStamp from the given user
     * @param rfidKey id of the user
     * @param stampId the TimeStamp to be deleted
     * @return the deleted time
     **/

    /**
     * Fetches all the times assigned to the user
     *
     * @param id the user
     * @return all the times for the user
     **/
    public ResponseEntity<ArrayList<TimeStamp>> getAll(String id);
    /**
     * Fetches all the times assigned to the user
     *
     * @param rfidKey the user
     * @return all the times for the user
     **/

    /**
     * Fetches the given time of a specific user
     *
     * @param id      the user
     * @param stampId the id of the time to be fetched
     * @return the time we searched for
     **/
    public ResponseEntity<TimeStamp> getTime(String id, String stampId);
    /**
     * Fetches the given time of a specific user
     *
     * @param rfidKey      the user
     * @param stampId the id of the time to be fetched
     * @return the time we searched for
     **/

    /**
     * Updates the given time of a specific user
     *
     * @param id              the user
     * @param stampId         the id of the time to be changed
     * @param updatedTimeJSON JSON of the updated object
     * @return updated time
     **/
    public ResponseEntity<TimeStamp> updateTime(String id, String stampId, Map<String, Object> updatedTimeJSON);
    /**
     * Updates the given time of a specific user
     *
     * @param rfidKey              the user
     * @param stampId         the id of the time to be changed
     * @param updatedTimeJSON JSON of the updated object
     * @return updated time
     **/
}

package org.demo.service;

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
    ResponseEntity<TimeStamp> addNowTime(String id);

    /**
     * Deletes the specified TimeStamp from the given user
     * @param id id of the user
     * @param stampId the TimeStamp to be deleted
     * @return the deleted time
     **/
    ResponseEntity<TimeStamp> deleteTime(String id, String stampId);
    /**
     * Fetches all the times assigned to the user
     *
     * @param id the user
     * @return all the times for the user
     **/
    ResponseEntity<ArrayList<TimeStamp>> getAll(String id);

    /**
     * Fetches the given time of a specific user
     *
     * @param id      the user
     * @param stampId the id of the time to be fetched
     * @return the time we searched for
     **/
    ResponseEntity<TimeStamp> getTime(String id, String stampId);

    /**
     * Updates the given time of a specific user
     *
     * @param id              the user
     * @param stampId         the id of the time to be changed
     * @param updateStamp JSON of the updated object
     * @return updated time
     **/
    ResponseEntity<TimeStamp> updateTime(String id, String stampId, TimeStamp updateStamp);

    /**
     * Updates the given time of a specific user
     *
     * @param id              the user
     * @param newStamp the new timestamp
     * @return updated time
     **/
	ResponseEntity<TimeStamp> addTime(String id, TimeStamp newStamp);

}

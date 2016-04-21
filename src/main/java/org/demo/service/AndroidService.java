package org.demo.service;
import org.demo.model.AndroidBetweenQuery;
import org.demo.model.AndroidStamp;
import org.demo.model.RfidKey;
import org.demo.model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

/**
 * Created by Anton on 2016-04-11.
 */

/**
 *Interface for methods used by the android clients
 **/
public interface AndroidService {

    /**
     * Fetches the user with the given RFID-key, the String gets converted into an RFID-key
     * @param id the RFID-key
     * @return the user
     **/
    public ResponseEntity<User> getUser( String id);

    /**
     * Fetches all times associated with the given user
     * @param rfidKey The user with RFID sent in a JSON
     * @return all the times
     **/
    public ResponseEntity<List<AndroidStamp>> getAll(RfidKey rfidKey);

    /**
     * Fetches the times between the given times/dates
     * @param androidBetweenQuery JSON containing the RFID of the user, the "from" date and the "to" date
     * @return the times in the interval
     **/
    public ResponseEntity<List<AndroidStamp>> getBetween(AndroidBetweenQuery androidBetweenQuery);

    public ResponseEntity<User> loginUser( Map<String, Object> getSpecificUserJSON);

}

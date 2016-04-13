package org.demo.service;
import org.demo.model.AndroidStamp;
import org.demo.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.ArrayList;
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
    public ResponseEntity<User> getUser(@PathVariable("id") String id);

    /**
     * Fetches all times associated with the given user
     * @param rfidkeyJSON The user with RFID sent in a JSON
     * @return all the times
     **/
    public ResponseEntity<ArrayList<AndroidStamp>> getAll(@RequestBody Map<String, Object> rfidkeyJSON);

    /**
     * Fetches the times between the given times/dates
     * @param betweenJSON JSON containing the RFID of the user, the "from" date and the "to" date
     * @return the times in the interval
     **/
    public ResponseEntity<ArrayList<AndroidStamp>> getBetween(@RequestBody Map<String, Object> betweenJSON);

    public ResponseEntity<User> loginUser(@RequestBody Map<String, Object> getSpecificUserJSON);

}

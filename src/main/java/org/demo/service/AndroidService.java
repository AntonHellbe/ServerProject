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
public interface AndroidService {

    public ResponseEntity<User> getUser(@PathVariable("id") String id);

    public ResponseEntity<ArrayList<AndroidStamp>> getAll(@RequestBody Map<String, Object> rfidkeyJSON);

    public ResponseEntity<ArrayList<AndroidStamp>> getBetween(@RequestBody Map<String, Object> betweenJSON);

}

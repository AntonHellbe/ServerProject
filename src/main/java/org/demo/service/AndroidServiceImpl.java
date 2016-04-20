package org.demo.service;

import org.demo.model.AndroidStamp;
import org.demo.model.RfidKey;
import org.demo.model.TimeStamp;
import org.demo.model.User;
import org.demo.repository.ListRepository;
import org.demo.repository.TimeRepository;
import org.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.websocket.server.ServerEndpoint;
import java.util.*;


/**
 * Created by Anton on 2016-04-11.
 */
/**
 * Class that handles methods used by the Android clients
 **/
@Service
public class AndroidServiceImpl implements AndroidService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TimeRepository timeRepository;

    /**
     * Fetches the user with the given RFID-key, the String gets converted into an RFID-key
     * @param id the RFID-key
     * @return the user
     **/

    public ResponseEntity<User> getUser(@PathVariable("id") String id) {
        System.out.println("looking for RFID " +id);
        User wantedUser = userRepository.findOne(id);
        System.out.println("found user: "+wantedUser);
        if(wantedUser != null){
            return new ResponseEntity<User>(wantedUser, HttpStatus.OK);
        }else {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }

    }

    public ResponseEntity<User> logInUser(@RequestBody Map<String, Object> getSpecificUserJSON) {
        System.out.println(getSpecificUserJSON.get("firstName").toString());
        System.out.println(getSpecificUserJSON.get("lastName").toString());
        List<User> userList = userRepository.findAll();
        for(int i=0; i<userList.size(); i++) {
            if(userList.get(i).getFirstName().equals(getSpecificUserJSON.get("firstName").toString()) &&
                    userList.get(i).getLastName().equals(getSpecificUserJSON.get("lastName").toString())){
                User wantedUser = userList.get(i);
                System.out.println(wantedUser.toString());
                return new ResponseEntity<User>(wantedUser, HttpStatus.OK);
            }
        }
        return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
    }


    /**
     * Fetches all times associated with the given user
     * @param rfidkeyJSON The user with RFID sent in a JSON
     * @return all the times
     **/

    // DILDO
    public ResponseEntity<ArrayList<AndroidStamp>> getAll(@RequestBody Map<String, Object> rfidkeyJSON) {
        rfidkeyJSON.forEach((s, o) -> {
            System.out.println("[KEY]"+s+" [VALUE]"+o);
        });

        RfidKey rfidKey = new RfidKey(rfidkeyJSON.get("id").toString());
        System.out.println("get all stamps for id "+rfidKey);

        ArrayList<AndroidStamp> allTimes = new ArrayList<>();
//        User currentUser = userMap.get(rfidKey);
        ArrayList<TimeStamp> userStamps = new ArrayList<>(timeRepository.getAllByRfid(rfidKey));
        userStamps.forEach(timeStamp -> {
            allTimes.add(new AndroidStamp(timeStamp.getDate(), timeStamp.getCheckIn()));

        });

        System.out.println("sending to client");
        allTimes.forEach(item -> System.out.println(item));

        if(allTimes != null) {
            return new ResponseEntity<ArrayList<AndroidStamp>>(allTimes, HttpStatus.OK);
        }else {
            return new ResponseEntity<ArrayList<AndroidStamp>>(HttpStatus.NOT_FOUND);
        }
        // Hello
    }

    /**
     * Fetches the times between the given times/dates
     * @param betweenJSON JSON containing the RFID of the user, the "from" date and the "to" date
     * @return the times in the interval
     **/
    public ResponseEntity<ArrayList<AndroidStamp>> getBetween(@RequestBody Map<String, Object> betweenJSON) {
        betweenJSON.forEach((s, o) -> {
            System.out.println("[KEY]"+s+" [VALUE]"+o);
        });

        String key = betweenJSON.get("id").toString();

//	    Date dt = new Date(betweenJSON.get("from").toString());

        Calendar fromDate = new GregorianCalendar();
        fromDate.setTimeInMillis(Long.parseLong(betweenJSON.get("from").toString()));
        Calendar toDate = new GregorianCalendar();
        toDate.setTimeInMillis(Long.parseLong(betweenJSON.get("to").toString()));

        RfidKey rfidKey = new RfidKey(key);

        ArrayList<TimeStamp> userStamps = new ArrayList<>(timeRepository.getAllByRfid(new RfidKey(key)));
        ArrayList<AndroidStamp> betweenTimes = new ArrayList<>();

        //1460384544658 -----1460384551260  &&(timeStamp.getDate().before(toDate))==true
        userStamps.forEach(timeStamp -> {
            System.out.println(timeStamp.getDate().getTimeInMillis());
            //System.out.println("From: " + fromDate + " To: " + toDate);
            // TODO Fix missing timestamp date check
            if((timeStamp.getDate().after(fromDate))==true &&(timeStamp.getDate().before(toDate))==true) {
                System.out.println("1");
                betweenTimes.add(new AndroidStamp(timeStamp.getDate(), timeStamp.getCheckIn()));
            }
        });

        if(betweenTimes != null) {
            return new ResponseEntity<ArrayList<AndroidStamp>>(betweenTimes, HttpStatus.OK);
        }else {
            return new ResponseEntity<ArrayList<AndroidStamp>>(HttpStatus.NOT_FOUND);
        }
    }
}

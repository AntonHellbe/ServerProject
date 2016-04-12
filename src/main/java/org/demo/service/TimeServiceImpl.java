package org.demo.service;

import org.demo.model.RfidKey;
import org.demo.model.TimeStamp;
import org.demo.model.User;
import org.demo.repository.ListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;

/**
 * Created by Robin_2 on 11/04/2016.
 */
/**
 * Contains methods that enables the use and handling of times in the server
 **/
@Service
public class TimeServiceImpl implements TimeService {

    @Autowired
    private ListRepository listRepository;

    public TimeServiceImpl(){}

    @Override
    public ResponseEntity<TimeStamp> addTime(String id) {
        return addTime(new RfidKey(id));
    }

    @Override
    public ResponseEntity<TimeStamp> addTime(RfidKey rfidKey) {
        User currentUser = listRepository.getUserMap().get(rfidKey);
        System.out.println(currentUser.toString());
        ArrayList<TimeStamp> temp = listRepository.getTimeStampMap().get(rfidKey);
        boolean state = true;

        TimeStamp newStamp = new TimeStamp(Calendar.getInstance(), state, rfidKey);

        if(temp == null) {
            newStamp.setCheckIn(state);
            temp = new ArrayList<TimeStamp>();
            temp.add(newStamp);
            listRepository.getTimeStampMap().put(rfidKey, temp);
        }else {
            state = listRepository.getTimeStampMap().get(rfidKey).size() % 2 == 0;
            newStamp.setCheckIn(state);
            listRepository.getTimeStampMap().get(rfidKey).add(newStamp);
        }
        return new ResponseEntity<>(newStamp, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TimeStamp> deleteTime(String id, int stampId) {
        return deleteTime(new RfidKey(id),stampId);
    }

    @Override
    public ResponseEntity<TimeStamp> deleteTime(RfidKey rfidKey, int stampId) {
        ArrayList<TimeStamp> userStamps = listRepository.getTimeStampMap().get(rfidKey);

	    TimeStamp removedTime = null;

	    for (TimeStamp stamp : userStamps) {
		    System.out.println("id in userStamps "+stamp.getId());
		    if (stamp.getId() == stampId) {
			    removedTime = stamp;
			    break;
		    }
	    }
	    //Didnt find the stamp
	    if (removedTime == null) {
		    System.out.println("Not found "+stampId);
		    return new ResponseEntity(HttpStatus.NOT_FOUND);
	    }

	    userStamps.remove(removedTime);
	    //listRepository.getTimeStampMap().replace(rfidKey, userStamps);



        // TODO: 2016-04-10 :00:40 hur vet klienten vad stampId:et ar!?
        return new ResponseEntity<TimeStamp>(removedTime, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ArrayList<TimeStamp>> getAll(String id) {
        return getAll(new RfidKey(id));
    }

    @Override
    public ResponseEntity<ArrayList<TimeStamp>> getAll(RfidKey rfidKey) {
        ArrayList<TimeStamp> allTimes = new ArrayList<>();
        ArrayList<TimeStamp> userStamps = listRepository.getTimeStampMap().get(rfidKey);
        if (userStamps == null) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
        }
        userStamps.forEach(timeStamp -> {
            allTimes.add(new TimeStamp(timeStamp.getDate(), timeStamp.getCheckIn(), timeStamp.getRfidkey()));

        });
        allTimes.forEach(timeStamp -> {
            System.out.println(timeStamp);
        });
        return new ResponseEntity<>(allTimes, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TimeStamp> getTime(String id, int stampId) {
        return getTime(new RfidKey(id), stampId);
    }

    @Override
    public ResponseEntity<TimeStamp> getTime(RfidKey rfidKey, int stampId) {
        ArrayList<TimeStamp> userStamps = listRepository.getTimeStampMap().get(rfidKey);
        if (userStamps.size() < stampId) {
            // TODO: 2016-04-11 :12:45 Fix a better return?
            return null;
        }
        return new ResponseEntity<>(userStamps.get(stampId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TimeStamp> updateTime(String id, int stampId, Map<String, Object> updatedTimeJSON) {
        return updateTime(new RfidKey(id), stampId, updatedTimeJSON);
    }

    @Override
    public ResponseEntity<TimeStamp> updateTime(RfidKey rfidKey, int stampId, Map<String, Object> updatedTimeJSON) {
        User currentUser = listRepository.getUserMap().get(rfidKey);
        ArrayList<TimeStamp> temp = listRepository.getTimeStampMap().get(currentUser.getRfid());
        TimeStamp timeToUpdate = temp.get(stampId);
        Calendar cal = new GregorianCalendar();
        if (updatedTimeJSON.get("date") != null) {
            long date = Long.parseLong(updatedTimeJSON.get("date").toString());
            cal.setTimeInMillis(date);
            timeToUpdate.setDate(cal);
        }
        if (updatedTimeJSON.get("checkIn") != null) {
            timeToUpdate.setCheckIn(updatedTimeJSON.get("checkIn").toString() == "true");
        }
        if (updatedTimeJSON.get("rfid") != null) {
            timeToUpdate.setRfidkey(new RfidKey(updatedTimeJSON.get("rfid").toString()));
        }
        return new ResponseEntity<>(timeToUpdate, HttpStatus.OK);
    }
}

package org.demo.controller;

import org.demo.model.*;
import org.demo.repository.ListRepository;
import org.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

/**
 * Created by Anton on 2016-04-07.
 */

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/android")
public class AndroidServiceController {

	@Autowired
	private ListRepository listRepository;


    //stringrfid key , User value
    private HashMap<RfidKey, User> userMap = new HashMap<>();
	HashMap<RfidKey, ArrayList<TimeStamp>> timeStampMap = new HashMap<>();

    public AndroidServiceController(){
		listRepository = new ListRepository();
		userMap = listRepository.getUserMap();

	    Calendar from= new GregorianCalendar(2014, 1, 06,10,00);
	    Calendar to= new GregorianCalendar(2014, 1, 06,16,00);

	    ArrayList<TimeStamp> timeStamps = new ArrayList<>();

	    timeStamps.add(new TimeStamp(from,true,new RfidKey("1")));
	    timeStamps.add(new TimeStamp(to,false,new RfidKey("1")));

	    timeStampMap.put(new RfidKey("1"),timeStamps);
	    timeStampMap.put(new RfidKey("2"),timeStamps);

//	    User user = userMap.get(new RfidKey("1"));
    }

    /**
     *Method that recives an id/username and returns the user it belongs too
     * @param id the id/username of the sought after user
     * @return The user we searched for
     **/
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public User getUser(@PathVariable("id") String id){
	    System.out.println("lokking ofr rfid "+id);
	    User wantedUser = this.userMap.get(new RfidKey(id));
        System.out.println("found user: "+wantedUser);
        return wantedUser;
    }

    /**
     * Used when you want to get all the times of a given user
     * @return all the times associated with the rfid-key
     **/
//    @RequestMapping(value = "/all", method = RequestMethod.GET)
//    @RequestMapping(value = "/all/{id}",method = RequestMethod.GET)
//    public ArrayList<AndroidStamp> getAll(@PathVariable("id") RfidKey rfidKey){
//
//	    System.out.println("get all stamps for id "+rfidKey);
//
//	    ArrayList<AndroidStamp> allTimes = new ArrayList<>();
////        User currentUser = userMap.get(rfidKey);
//	    ArrayList<TimeStamp> userStamps = this.timeStampMap.get(rfidKey);
//	    userStamps.forEach(timeStamp -> {
//		    allTimes.add(new AndroidStamp(timeStamp.getDate(), timeStamp.getCheckIn()));
//
//	    });
//
//	    System.out.println("sending to client");
//	    allTimes.forEach(item -> System.out.println(item));
//
//        return allTimes;
//    }



	@RequestMapping(value = "/all",method = RequestMethod.POST)
	public ArrayList<AndroidStamp> getAll(@RequestBody Map<String, Object> rfidkeyJSON){

		rfidkeyJSON.forEach((s, o) -> {
			System.out.println("[KEY]"+s+" [VALUE]"+o);
		});

		RfidKey rfidKey = new RfidKey(rfidkeyJSON.get("id").toString());
		System.out.println("get all stamps for id "+rfidKey);

		ArrayList<AndroidStamp> allTimes = new ArrayList<>();
//        User currentUser = userMap.get(rfidKey);
		ArrayList<TimeStamp> userStamps = this.timeStampMap.get(rfidKey);
		userStamps.forEach(timeStamp -> {
			allTimes.add(new AndroidStamp(timeStamp.getDate(), timeStamp.getCheckIn()));

		});

		System.out.println("sending to client");
		allTimes.forEach(item -> System.out.println(item));

		return allTimes;
//		return new ResponseEntity<String>("hello", HttpStatus.OK);
	}



    /**
     * Used when you want to get all the times of a given user
     * @return the times associated with the rfid-key and in the period given
     **/
//
//    @RequestMapping(value = "/all",method = RequestMethod.POST)
//    public ArrayList<AndroidStamp> getAll(@RequestBody Map<String, Object> betweenJSON){


    @RequestMapping(value = "/between", method = RequestMethod.POST)
    public ArrayList<AndroidStamp> getBetween(@RequestBody Map<String, Object> betweenJSON){

	    betweenJSON.forEach((s, o) -> {
		    System.out.println("[KEY]"+s+" [VALUE]"+o);
	    });

	    String key = betweenJSON.get("id").toString();

//	    Date dt = new Date(betweenJSON.get("from").toString());

	    Long fromDate = (Long) betweenJSON.get("from");
	    Long  toDate = (Long) betweenJSON.get("to");
	    Calendar from = new GregorianCalendar();
	    from.setTimeInMillis(fromDate);

	    Calendar to= new GregorianCalendar();
	    from.setTimeInMillis(toDate);


	    RfidKey rfidKey = new RfidKey(key);

	    ArrayList<TimeStamp> userStamps = this.timeStampMap.get(rfidKey);
	    ArrayList<AndroidStamp> betweenTimes = new ArrayList<>();

	    userStamps.forEach(timeStamp -> {
		    // TODO Fix missing timestamp date check
//		    if () {
//		    }
//		    if(timeStamp.getDate().getTime().compareTo(from.getTime())){
////
//		    }
		    betweenTimes.add(new AndroidStamp(timeStamp.getDate(), timeStamp.getCheckIn()));

	    });



        User currentUser = userMap.get(rfidKey);
        return betweenTimes;
    }
}

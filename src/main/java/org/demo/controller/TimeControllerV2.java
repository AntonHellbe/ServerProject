package org.demo.controller;

import org.demo.model.TimeSamples;
import org.demo.model.User;
import org.demo.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 */

/**
 * Created by Anton on 2016-04-05.
 */

/*
* Endpoints: localhost:8080/api/time
*             localhost:8080/api/time/{id}
*             localhost:8080/api/time/{id}/times
**/
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/time")
public class TimeControllerV2 {
	private ArrayList<TimeSamples> timeStamps = new ArrayList<>();
	private ArrayList<User> userList = new ArrayList<>();
	//stringrfid key , User value
	private HashMap<String, User> userMap = new HashMap<>();

	public TimeControllerV2() {
		MemberService test = new MemberService();
		try {
			userMap = test.loadMember();
			userList = test.getUsers();
		} catch (IOException e) {
			e.printStackTrace();
		}

		userMap.forEach((s, user) -> {
			System.out.println("[KEY]: "+s+" [Value]: "+user);
		});


		User temp = userMap.get("1");
		temp.newSample();
		temp.newSample();

	}

	@RequestMapping(method = RequestMethod.POST)
	public User timeStamps(@RequestBody User usertest) {
		System.out.println("add User");
		System.out.println(usertest);
		this.userList.add(usertest);
		User addUser = this.userMap.put(usertest.getRfid(), usertest);

		return addUser;
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public User deleteTimeStamp(@PathVariable("id") String id) {
		System.out.println("remove id: " + id);
		//find the user in the map
		User theUser = this.userMap.get(id);
		System.out.println("removing user: "+theUser.getFirstName());
		//remove the user from the map
		User removedUser = this.userMap.remove(id);

		//remove the user from the list
		this.userList.remove(theUser);

		userMap.forEach((s, user) -> {
			System.out.println("[KEY]: "+s+" [Value]: "+user.getFirstName());
		});

		User.setSecretId(userList.size());

		System.out.println();
		System.out.println();

		userList.forEach(user -> System.out.println(user.getFirstName()));

		//return the removed user
		return removedUser;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ArrayList<User> getAllTimeStamps() {
		System.out.println("Get all users");
		Map<String, Object> response = new LinkedHashMap<>();
		response.put("totalTimestamps", timeStamps.size());
		response.put("Users", this.userList);
		return this.userList;
	}


	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public User getTimeStamp(@PathVariable("id") String id) {
		System.out.println("Get the user with id: "+id);
		//get a user with RFID
		User gotUser = this.userMap.get(id);

//		new ResponseEntity<User>("Couldnt find User", HttpStatus.NOT_FOUND);
//        return new ResponseEntity<User>(current, HttpStatus.OK);
		return gotUser;
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public User updateUser(@PathVariable("id") String id,
	                       @RequestBody Map<String, Object> updatedUserJSON) {

		System.out.println("Update user: "+id);

		//what did u get from client
		updatedUserJSON.forEach((key, obj) -> {
			System.out.println("KEY: "+key);
			System.out.println("VALUE: "+obj.toString());
		});

		//get hte user
		User currentUser = userMap.get(id);

		//check if i should update firstName
		if (updatedUserJSON.get("firstName") != null) {
			System.out.println("update firstName");
			currentUser.setFirstName(updatedUserJSON.get("firstName").toString());
		}

		if (updatedUserJSON.get("lastName") != null) {
			System.out.println("update lastName");
			currentUser.setLastName(updatedUserJSON.get("lastName").toString());
		}

		if (updatedUserJSON.get("rfid") != null) {
			System.out.println("update rfid");
			currentUser.setRfid(updatedUserJSON.get("rfid").toString());
		}

		User updatedUser = userMap.replace(id, currentUser);
		userList.set(Integer.parseInt(id)-1, updatedUser);


		System.out.println("updated map");
		userMap.forEach((s, user) -> {
			System.out.println("[KEY]: "+s+" [Value]: "+user);
		});

		return updatedUser;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}/newTime")
	public User timeStampUser (@PathVariable("id") String id){
		User timeUser = this.userMap.get(id);
		timeUser.newSample();

		return timeUser;
	}


}

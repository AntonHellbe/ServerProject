package org.demo.service.databaseservice;

import org.demo.model.RfidKey;
import org.demo.model.User;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Anton on 2016-04-06.
 */

/**
 * Temporary class along with the interface, loads in a text file filled with:
 * userNames, id and RFID
 **/
@Component
public class MemberService {

    private static HashMap<RfidKey, User> userMap;
    private String currentUserID;
    private InputStream path;


    /**
     * Calls the private method "loadMember(String path)"
     * Reads from file and places data in a BST
     **/
	public HashMap<RfidKey, User> loadMember() throws IOException {
        return loadMember(path);
    }

    /**
     * Reads from file and places data in a BST
     *
     * @param path file path to file with members
     * @return Filled out userMap, with all members added
     **/

    private HashMap<RfidKey, User> loadMember(InputStream path) throws IOException {

	    ClassPathResource cpr = new ClassPathResource("files/Lantagare.txt");

        userMap = new HashMap<>();
        BufferedReader br;
        try {
	        br = new BufferedReader(new InputStreamReader(cpr.getInputStream()));
        } catch (Exception e) {
            throw new FileNotFoundException("Couldnt find MemberService File");
        }

        String[] parts;
        String text = br.readLine();
        while (text != null) {
	        System.out.println(text);
	        parts = text.split(";");
            User user = new User(parts[0], parts[1], new RfidKey(parts[2]), parts[3]);
            userMap.put(user.getRfid(), user);
            text = br.readLine();
        }
        br.close();

        return userMap;
    }

    /**
     * Returns a list of all registered members
     *
     * @return List of members
     **/
    public HashMap<RfidKey, User> getMap() {
        return userMap;
    }

    /**
     * Checks if the current user is in the the list of registered users
     *
     * @return true/false depending if the user exists
     **/
    public boolean userExists() throws NullPointerException {
        try {
            return userMap.containsValue(currentUserID);
        } catch (Exception e) {
            throw new NullPointerException("Null pointer exception on finding member");
        }
    }

    /**
     * Sets the current user to the input
     * <p>
     * //* @param currentUser the user to be the current one
     **/

    public void setCurrentUserID(String rfid) {
        this.currentUserID = rfid;
    }

    public ArrayList<User> getUsers() {
        return new ArrayList<User>(userMap.values());
    }

    /**
     * Fetches a specified member through the use of their memberID
     **/
    public User getCurrentUser() {
        return this.userMap.get(currentUserID);
    }
}
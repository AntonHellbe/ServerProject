package org.demo.service;

import org.demo.model.RfidKey;
import org.demo.model.User;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Anton on 2016-04-06.
 */
public class MemberService {

    private static HashMap<RfidKey, User> userMap;
    private String currentUserID;
    private InputStream path;

    public MemberService(String path) {
//        this.path = getClass().getResourceAsStream("/" + path);
        this.path = getClass().getResourceAsStream("src/main/java/org/demo/files/Lantagare.txt");
    }

	public MemberService() {

	}

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

	    String thePath = "src/main/java/org/demo/files/Lantagare.txt";
        userMap = new HashMap<>();
        BufferedReader br;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(thePath)));
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

//    public static void main(String[] args) throws IOException {
////	    File r = new File("src/main/java/org/demo/files/Lantagare.txt");
//        MemberService dildo = new MemberService();
//
////        HashMap<String, User> hitler = dildo.getUsers();
////        System.out.println(hitler);
//
//	    //find file path
//	    Files.walk(Paths.get("src/main/java/org/demo/files")).forEach(filepath ->{
//		    if (Files.isRegularFile(filepath)) {
//			    System.out.println(filepath);
//		    }
//	    });
//    }
}
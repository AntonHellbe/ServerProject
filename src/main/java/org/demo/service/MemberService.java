package org.demo.service;

import org.demo.model.User;

import java.io.*;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Anton on 2016-04-06.
 */
public class MemberService {

    private static HashMap<String, User> memberTree;
    private String currentUserID;
    private InputStream path;

    public MemberService(String path) {
        this.path = getClass().getResourceAsStream("/" + path);
    }

    public void loadMember() throws IOException {
        loadMember(path);
    }

    /**
     * Reads from file and places data in a BST
     *
     * @param path file path to file with members
     * @return Filled out memberTree, with all members added
     **/

    private HashMap<String, User> loadMember(InputStream path) throws IOException {

        memberTree = new HashMap<>();
        BufferedReader br;
        try {
            br = new BufferedReader(new InputStreamReader(path));
        } catch (Exception e) {
            throw new FileNotFoundException("Couldnt find MemberService File");
        }

        String[] parts;
        String text = br.readLine();
        while (text != null) {
            parts = text.split(";");
            User user = new User(parts[0], parts[1], parts[2], parts[3]);
            memberTree.put(user.getRfid(), user);
            text = br.readLine();
        }
        br.close();

        return memberTree;
    }

    /**
     * Returns a list of all registered members
     *
     * @return List of members
     **/
    public HashMap<String, User> getUsers() {
        return memberTree;
    }

    /**
     * Checks if the current user is in the the list of registered users
     *
     * @return true/false depending if the user exists
     **/
    public boolean userExists() throws NullPointerException {
        try {
            return memberTree.containsValue(currentUserID);
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


    /**
     * Fetches a specified member through the use of their memberID
     **/
    public User getCurrentUser() {
        return this.memberTree.get(currentUserID);
    }

    public static void main(String[] args) throws IOException {
        MemberService dildo = new MemberService("org/demo/files/Lantagare.txt");
        dildo.loadMember();
        HashMap<String, User> hitler = dildo.getUsers();
        System.out.println(hitler);
    }
}
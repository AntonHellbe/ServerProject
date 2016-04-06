package org.demo.service;

import org.demo.model.User;
import java.io.IOException;
import java.util.List;


/**
 * @author Anton Hellbe
 */
public interface IMemberService {

    /**
     * Calls the private method "loadMember(String path)"
     * Reads from file and places data in a BST
     **/
    void loadMember() throws IOException;

    /**
     * Returns a list of all registered members
     * @return List of members
     **/
    List<User> getMembers();

    /**
     * Sets the current user to the input
     * @param currentUser the user to be the current one
     **/
    void setCurrentUserID(String currentUser);

    /**
     * Checks if the current user is in the the list of registered users
     * @return true/false depending if the user exists
     **/
    boolean userExists() throws NullPointerException;

    /**
     * Fetches a specified member through the use of CurrentUserID
     **/
    public User getCurrentUser();

}

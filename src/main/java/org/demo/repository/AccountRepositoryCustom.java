package org.demo.repository;

import org.demo.config.AuthoritiesConstants;
import org.demo.model.RfidKey;
import org.demo.model.security.Account;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.List;

/**
 * @Author Anton Hellbe
 * Custom query class for account objects in the database.
 */
public interface AccountRepositoryCustom {

    /**
     * Finds an user based on the RFID-key in the database
     * @param rfidKey to look for
     * @return Account objekt associated to the RFID-key
     */

    Account findUserByRfid(RfidKey rfidKey);

    /**
     * Finds Accounts based on Roles
     * @param authorityList list of roles to look for
     * @return List of Accounts associated to the roles
     */

    List<Account> findByRole(List<GrantedAuthority> authorityList);

    /**
     * Finds an Account by UserName
     * @param userName to look for
     * @return Account object associated to the userName
     */

    Account findByUserName(String userName);

    /**
     * Returns a list with Accounts with the specific firstName and lastName
     * @param firstName to look for in the database
     * @param lastName to look for in the database
     * @return List of the Account objects with the specific firstName & lastName
     */

    Account findByName(String firstName, String lastName);

    /**
     * Finds users by status, either Disabled/Enabled
     * @param status True / False depending on what status we are looking for
     * @return list of all the Accounts with the specific status
     */

    List<Account> findDisabledUsers(boolean status);

    /**
     * Finds all the users with an RFID-key assigned
     * @return list of all users with an RFID-key assigned
     */

    List<Account> findAllRfidUsers();


}

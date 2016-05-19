package org.demo.repository;

import org.demo.config.AuthoritiesConstants;
import org.demo.model.RfidKey;
import org.demo.model.security.Account;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.List;

/**
 * Created by Anton on 2016-04-27.
 */
public interface AccountRepositoryCustom {

    Account findUserByRfid(RfidKey rfidKey);

    List<Account> findByRole(List<GrantedAuthority> authorityList);

    Account findByUserName(String userName);

    Account findByName(String firstName, String lastName);

    List<Account> findDisabledUsers(String status);

    List<Account> findAllRfidUsers();

//    boolean existingUser(String userName);

}

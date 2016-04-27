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

    public Account findUserByRfid(RfidKey rfidKey);

    public List<Account> findByRole(List<GrantedAuthority> authorityList);

    public Account findByUserName(String userName);

    public Account findByName(String firstName, String lastName);

    public List<Account> findDisabledUsers(String status);

}

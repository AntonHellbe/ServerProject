package org.demo.repository;

import org.demo.model.RfidKey;
import org.demo.model.security.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

/**
 * Created by Anton on 2016-04-27.
 */
public class AccountRepositoryImpl implements AccountRepositoryCustom {

    @Autowired
    private MongoOperations mongoOperations;

    public Account findUserByRfid(RfidKey rfidKey) {
        return mongoOperations.findOne(query(where("rfidKey").is(rfidKey)), Account.class);
    }

    public List<Account> findByRole(List<GrantedAuthority> authorityList){
        return mongoOperations.find(query(where("authorties.role").is(authorityList.get(0))), Account.class);
    }

    public Account findByUserName(String userName) {
        return mongoOperations.findOne(query(where("username").is(userName)), Account.class);
    }

    @Override
    public Account findByName(String firstName, String lastName) {
        return mongoOperations.findOne(query(where("firstName").is(firstName).and("lastName").is(lastName)), Account.class);
    }

    @Override
    public List<Account> findDisabledUsers(String status) {
        return mongoOperations.find(query(where("isEnabled").is(false)), Account.class);

    }


}

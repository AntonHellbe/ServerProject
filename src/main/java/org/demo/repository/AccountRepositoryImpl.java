package org.demo.repository;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import org.demo.model.RfidKey;
import org.demo.model.security.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

/**
 * @Author Anton Hellbe
 */
public class AccountRepositoryImpl implements AccountRepositoryCustom {

    @Autowired
    private MongoOperations mongoOperations;

    public Account findUserByRfid(RfidKey rfidKey) {
        return mongoOperations.findOne(query(where("rfidKey._id").is(rfidKey.getId())), Account.class);
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
    public List<Account> findDisabledUsers(boolean status) {
        return mongoOperations.find(query(where("isEnabled").is(status)), Account.class);

    }

    @Override
    public List<Account> findAllRfidUsers() {
        return mongoOperations.find(query(where("rfidKey").exists(true)), Account.class);
    }




//    @Override
//    public boolean existingUser(String userName) {
//        return mongoOperations.findOne(query(where("userName").equals(userName)), boolean.class);
//    }


}

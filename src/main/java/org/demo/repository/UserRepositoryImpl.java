package org.demo.repository;

import org.bson.types.ObjectId;
import org.demo.model.RfidKey;
import org.demo.model.Stamp;
import org.demo.model.TimeStamp;
import org.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;


import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;


/**
 * Created by Anton on 2016-04-15.
 */
public class UserRepositoryImpl implements UserRepositoryCustom {

    @Autowired
    private MongoOperations mongoOperations;

    @Override
    public User getUserByRfid(RfidKey rfidKey) {
        return mongoOperations.findOne(query(where("rfidKey").is(rfidKey)), User.class);
    }

}

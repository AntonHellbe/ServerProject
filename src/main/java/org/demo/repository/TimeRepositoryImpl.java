package org.demo.repository;

import org.demo.model.RfidKey;
import org.demo.model.TimeStamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

/**
 * Created by Anton on 2016-04-20.
 */
public class TimeRepositoryImpl implements TimeRepositoryCustom {

    @Autowired
    private MongoOperations mongoOperations;

    public List<TimeStamp> getByRfid(RfidKey rfidKey) {
        return mongoOperations.find(query(where("RfidKey").is(rfidKey)), TimeStamp.class);
    }
}

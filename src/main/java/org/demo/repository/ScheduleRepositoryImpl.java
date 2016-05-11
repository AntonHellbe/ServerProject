package org.demo.repository;

import org.demo.model.RfidKey;
import org.demo.model.ScheduleStamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

/**
 * Created by Anton on 2016-05-10.
 */
public class ScheduleRepositoryImpl implements ScheduleRepositoryCustom {

    @Autowired
    MongoOperations mongoOperations;

    @Override
    public List<ScheduleStamp> getByRfid(RfidKey rfidKey) {
        return mongoOperations.find(query(where("rfidKey").is(rfidKey)), ScheduleStamp.class);
    }

    public List<ScheduleStamp> getBetweenQuery(long from, long to, String id) {
        return mongoOperations.find(query(where("from").gte(from).and("to").lte(to).and("userId").is(id)), ScheduleStamp.class);
    }


}

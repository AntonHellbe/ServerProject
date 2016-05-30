package org.demo.repository;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.demo.model.RfidKey;
import org.demo.model.ScheduleStamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

/**
 * @Author Anton Hellbe
 * Custom Query class for ScheduleStamp objects in the database collection ScheduleStamps
 */

public class ScheduleRepositoryImpl implements ScheduleRepositoryCustom {

    @Autowired
    MongoOperations mongoOperations;

    @Override
    public List<ScheduleStamp> getById(String id) {
        return mongoOperations.find(query(where("userId.").is(id)), ScheduleStamp.class);
    }

    public List<ScheduleStamp> getBetweenQuery(long from, long to, String id) {
        return mongoOperations.find(query(where("from").gte(from).and("to").lte(to).and("userId.").is(id)), ScheduleStamp.class);
    }

    public List<ScheduleStamp> getByIds(List<String> ids){
        return mongoOperations.find(query(where("userId").is(ids.get(0)).and("userId").is(ids.get(1))), ScheduleStamp.class);
    }

    public List<ScheduleStamp> getAfter(String id, long date) {
        return mongoOperations.find(query(where("date").gt(date).and("userId.").is(id)), ScheduleStamp.class);
    }




}

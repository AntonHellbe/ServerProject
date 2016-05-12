package org.demo.repository;

import org.demo.model.AndroidBetweenQuery;
import org.demo.model.RfidKey;
import org.demo.model.ScheduleStamp;
import org.demo.model.TimeStamp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.security.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

/**
 * Created by Anton on 2016-04-20.
 */
public class TimeRepositoryImpl implements TimeRepositoryCustom {

    private static final Logger log = LoggerFactory.getLogger(TimeRepositoryImpl.class);

    @Autowired
    private MongoOperations mongoOperations;

    public List<TimeStamp> getByRfid(RfidKey rfidKey) {
        return mongoOperations.find(query(where("RfidKey").is(rfidKey)), TimeStamp.class);
    }

    @Override
    public List<TimeStamp> getBetween(AndroidBetweenQuery androidBetweenQuery){
	    log.info("got between "+androidBetweenQuery.toString());
        long from = androidBetweenQuery.getFrom();
        long to = androidBetweenQuery.getTo();
        RfidKey key = androidBetweenQuery.getId();
//      Query query = new Query().addCriteria(Criteria.where("time").gte(from).lte(to).and("rfidKey").is(key));
//      return mongoOperations.find(query, TimeStamp.class);

//	    List<TimeStamp> got = mongoOperations.find(query(where("rfidKey").is(key)), TimeStamp.class);
//	    return  got;
	    return mongoOperations.find(query(where("date.time").gte(androidBetweenQuery.getFrom()).lte(androidBetweenQuery.getTo()).and("rfidKey").is(androidBetweenQuery.getId())), TimeStamp.class);

    }
}

package org.demo.repository;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import org.bson.types.ObjectId;
import org.demo.model.AndroidBetweenQuery;
import org.demo.model.RfidKey;
import org.demo.model.ScheduleStamp;
import org.demo.model.TimeStamp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
	    return mongoOperations.find(query(where("date").gte(androidBetweenQuery.getFrom()).lte(androidBetweenQuery.getTo()).and("rfidKey._id").is(androidBetweenQuery.getId().getId())),
                TimeStamp.class);
    }

    @Override
    public TimeStamp stateCheck(RfidKey rfidKey) {
        Query query = new Query();
        query.limit(1);
        query.with(new Sort(Sort.Direction.DESC, "date")).addCriteria(Criteria.where("rfidKey._id").is(rfidKey.getId()));
        TimeStamp got = mongoOperations.findOne(query, TimeStamp.class);
        return got;
    }

    public TimeStamp findCertainTime(TimeStamp timeStamp) {
        return mongoOperations.findOne(query(where("time").is(timeStamp.getDate()).and("rfidKey._id").is(timeStamp.getRfidkey().getId())), TimeStamp.class);
    }

    public TimeStamp newStampCheck(RfidKey rfidKey, TimeStamp newStamp) {
        Query query = new Query();
        query.limit(1);
        query.with(new Sort(Sort.Direction.DESC, "date")).addCriteria(Criteria.where("rfidKey._id").is(rfidKey.getId()).and("date").lte(newStamp.getDate()));
        TimeStamp got = mongoOperations.findOne(query, TimeStamp.class);
        return got;
    }

}

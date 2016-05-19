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
        long from = androidBetweenQuery.getFrom();
        long to = androidBetweenQuery.getTo();
        RfidKey key = androidBetweenQuery.getId();
//      Query query = new Query().addCriteria(Criteria.where("time").gte(from).lte(to).and("rfidKey").is(key));
//      return mongoOperations.find(query, TimeStamp.class);

//	    List<TimeStamp> got = mongoOperations.find(query(where("rfidKey").is(key)), TimeStamp.class);
//	    return  got;
	    return mongoOperations.find(query(where("date").gte(androidBetweenQuery.getFrom()).lte(androidBetweenQuery.getTo()).and("rfidKey").is(androidBetweenQuery.getId())), TimeStamp.class);

    }

    @Override
    public TimeStamp stateCheck(RfidKey rfidKey) {
//      BasicDBObject query = new BasicDBObject();
//      return mongoOperations.find(query.)
//        BasicDBObject query = new BasicDBObject();
        Query query = new Query();
//         query.with("date.time", -1).addCriteria(Criteria.where("rfidKey._id").is("34915AEC"));
        query.limit(1);
        query.with(new Sort(Sort.Direction.DESC, "date")).addCriteria(Criteria.where("rfidKey._id").is(rfidKey.getId()));

        TimeStamp got = mongoOperations.findOne(query, TimeStamp.class);
//        query.put("date.time", -1);
//        DBCursor dbCursor = mongoOperations.getCollection("timestamps").find().limit(1).sort(query);
//
//        query(Criteria.where("rfidKey").is(new RfidKey("34915AEC")));
//      while(dbCursor.hasNext()) {
//          System.out.println(dbCursor.next().);
//      }
        return got;
    }

    public TimeStamp findCertainTime(TimeStamp timeStamp) {
        return mongoOperations.findOne(query(where("time").is(timeStamp.getDate()).and("rfidKey._id").is(timeStamp.getRfidkey().getId())), TimeStamp.class);
    }
}

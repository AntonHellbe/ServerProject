package org.demo.repository;

import org.demo.model.AndroidBetweenQuery;
import org.demo.model.RfidKey;
import org.demo.model.TimeStamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Calendar;
import java.util.GregorianCalendar;
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

    @Override
    public List<TimeStamp> getBetween(AndroidBetweenQuery androidBetweenQuery){
        Calendar fromDate = new GregorianCalendar();
        Calendar toDate = new GregorianCalendar();
        fromDate.setTimeInMillis(androidBetweenQuery.getFrom());
        toDate.setTimeInMillis(androidBetweenQuery.getTo());

        return mongoOperations.find(query(where("RfidKey").is(androidBetweenQuery.getId()).and(String.valueOf(fromDate.after(fromDate))).and(String.valueOf(fromDate.before(toDate)))), TimeStamp.class);

    }
}

package org.demo.repository;

import org.demo.model.AndroidBetweenQuery;
import org.demo.model.RfidKey;
import org.demo.model.TimeStamp;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Anton on 2016-04-20.
 */
public interface TimeRepositoryCustom {

    public List<TimeStamp> getByRfid(RfidKey rfidKey);

    public List<TimeStamp> getBetween(AndroidBetweenQuery androidBetweenQuery);

    public TimeStamp stateCheck(RfidKey rfidKey);

    public TimeStamp findCertainTime(TimeStamp timeStamp);
}

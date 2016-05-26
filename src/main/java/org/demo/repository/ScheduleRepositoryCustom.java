package org.demo.repository;

import org.demo.model.RfidKey;
import org.demo.model.ScheduleStamp;

import java.util.List;

/**
 * Created by Anton on 2016-05-10.
 */
public interface ScheduleRepositoryCustom {

    List<ScheduleStamp> getById(String id);

    List<ScheduleStamp> getBetweenQuery(long from, long to, String id);

    List<ScheduleStamp> getAfter(String id, long date);

    List<ScheduleStamp> getByIds(List<String> ids);
}

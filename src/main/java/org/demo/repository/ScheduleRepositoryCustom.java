package org.demo.repository;

import org.demo.model.RfidKey;
import org.demo.model.ScheduleStamp;

import java.util.List;

/**
 * @Author Anton Hellbe
 * Custom query class for ScheduleStamp objects in the Database
 */
public interface ScheduleRepositoryCustom {

    /**
     * Gets all the ScheduleStamps for a specific ID
     * @param id of the user we are looking for
     * @return List of all the ScheduleStamps for a specific ID
     */

    List<ScheduleStamp> getById(String id);

    /**
     * Gets all the ScheduleStamps between two dates for a specific ID
     * @param from date
     * @param to date
     * @param id specific ID to look for
     * @return list with ScheduleStamps between these two dates
     */

    List<ScheduleStamp> getBetweenQuery(long from, long to, String id);

    /**
     * Gets all the ScheduleStamps after a specific date for a specific user
     * @param id of the Account
     * @param date to get stamps after
     * @return List with all the ScheduleStamps
     */

    List<ScheduleStamp> getAfter(String id, long date);

    /**
     * Gets all the ScheduleStamps for more than one id;
     * @param ids list of the ID:s were looking for
     * @return list with all the ScheduleStamps
     */

    List<ScheduleStamp> getByIds(List<String> ids);
}

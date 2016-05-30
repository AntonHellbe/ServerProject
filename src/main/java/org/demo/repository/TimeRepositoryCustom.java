package org.demo.repository;

import org.demo.model.AndroidBetweenQuery;
import org.demo.model.RfidKey;
import org.demo.model.TimeStamp;

import java.util.Calendar;
import java.util.List;

/**
 * @Author Anton Hellb
 * Custom Query class for TimeStamps objects in the Database
 */
public interface TimeRepositoryCustom {

    /**
     * Gets a list of all the timestamps for a specific RFID-key
     * @param rfidKey associated to the timestamps
     * @return list of all the timestamps for the specific RFID-key
     */
    public List<TimeStamp> getByRfid(RfidKey rfidKey);

    /**
     * Gets a list of all the timestamps between two times
     * @param androidBetweenQuery contains RFID-key and the times to get TimeStamps between
     * @return list of the timestamps
     */

    public List<TimeStamp> getBetween(AndroidBetweenQuery androidBetweenQuery);

    /**
     * Returns the latest timeStamp for a specific RFID-key
     * @param rfidKey to look for
     * @return latest TimeStamp for specific RFID-key
     */

    public TimeStamp stateCheck(RfidKey rfidKey);

    /**
     * Method to avoid conflicts (two timestamps on the same time for the same RFID-key), returns a timestamp if there is already a timestamp as the newly
     * created timestamp
     * @param timeStamp new timestamp to add
     * @return a timestamp if there is already existing one with the same time
     */

    public TimeStamp findCertainTime(TimeStamp timeStamp);

    /**
     * Returns timestamp that comes before the newly created timestamp to retrieve the status
     * @param rfidKey of the newly created stamp
     * @param newStamp the newly created timestamp
     * @return TimeStamp that comes before the newly created stamp (Sorted by Time)
     */

    public TimeStamp newStampCheck(RfidKey rfidKey, TimeStamp newStamp);
}

package org.demo.repository;

import org.demo.model.RfidKey;
import org.demo.model.TimeStamp;

import java.util.List;

/**
 * Created by Anton on 2016-04-20.
 */
public interface TimeRepositoryCustom {
    public List<TimeStamp> getByRfid(RfidKey rfidKey);
}

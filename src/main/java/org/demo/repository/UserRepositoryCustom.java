package org.demo.repository;

import org.bson.types.ObjectId;
import org.demo.model.RfidKey;
import org.demo.model.TimeStamp;
import org.demo.model.User;

/**
 * Created by Anton on 2016-04-15.
 */
public interface UserRepositoryCustom {

    public User getUserByRfid(RfidKey rfidKey);


}

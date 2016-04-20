package org.demo.repository;

import org.demo.model.RfidKey;
import org.demo.model.User;

/**
 * Created by Anton on 2016-04-20.
 */
public interface UserRepositoryCustom {

    public User findUserByRfid(RfidKey rfidKey);
}

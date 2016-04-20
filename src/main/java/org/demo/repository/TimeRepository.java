package org.demo.repository;

import org.demo.model.TimeStamp;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Anton on 2016-04-19.
 */
@Repository
public interface TimeRepository extends MongoRepository<TimeStamp, String>, TimeRepositoryCustom {

}

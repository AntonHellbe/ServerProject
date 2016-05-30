package org.demo.repository;

import org.demo.model.TimeStamp;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Anton Hellbe
 * Class used to connect to the MongoDB Database
 */
@Repository
public interface TimeRepository extends MongoRepository<TimeStamp, String>, TimeRepositoryCustom {
}

package org.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Anton on 2016-04-05.
 */
@Repository
public interface TimeRepository extends MongoRepository<TimeStamps, String> {


}

package org.demo.repository;

import org.demo.model.ScheduleStamp;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Anton on 2016-05-10.
 */
@Repository
public interface ScheduleRepository extends MongoRepository<ScheduleStamp, String>, ScheduleRepositoryCustom {

}

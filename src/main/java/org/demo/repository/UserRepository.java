package org.demo.repository;

import org.demo.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Anton on 2016-04-20.
 */
@Repository
public interface UserRepository extends MongoRepository<User, String> {

}

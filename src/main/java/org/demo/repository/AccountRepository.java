package org.demo.repository;

import org.demo.model.security.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author Anton Hellbe
 *
 * Class used to connect to the MongoDB Database
 */
@Repository
public interface AccountRepository extends MongoRepository<Account, String>, AccountRepositoryCustom {


}
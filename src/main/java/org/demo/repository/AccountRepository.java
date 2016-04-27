package org.demo.repository;

import org.demo.model.security.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Sebastian Börebäck on 2016-04-22.
 */
@Repository
public interface AccountRepository extends MongoRepository<Account, String>, AccountRepositoryCustom {


}
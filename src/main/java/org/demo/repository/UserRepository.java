package org.demo.repository;

import org.demo.model.RfidKey;
import org.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Anton on 2016-04-15.
 */
@Repository
public interface UserRepository extends MongoRepository<User, String>, UserRepositoryCustom{

    public List<User> findAll();

    public List<User> findByFirstName(String firstName);

    public List<User> findByLastName(String lastName);


}

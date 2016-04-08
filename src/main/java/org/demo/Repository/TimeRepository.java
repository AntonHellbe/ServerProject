package org.demo.Repository;

import org.demo.model.RfidKey;
import org.demo.model.User;
import org.demo.service.MemberService;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Robin_2 on 08/04/2016.
 */
@Component
public class TimeRepository {


    private HashMap<RfidKey, User> userMap;
    private String hello = "hello";

    public HashMap<RfidKey, User> getUserMap() {
        return userMap;
    }

    public TimeRepository() {
        MemberService test = new MemberService();
        try {
            userMap = test.loadMember();
        } catch (IOException e) {

            e.printStackTrace();
        }

    }

    public String getHello() {
        return hello;
    }

    public void setHello(String hello) {
        this.hello = hello;
    }
}

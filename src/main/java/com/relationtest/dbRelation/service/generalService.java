package com.relationtest.dbRelation.service;


import com.relationtest.dbRelation.models.User;
import com.relationtest.dbRelation.repository.UserRpository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class generalService {


    @Autowired
    private UserRpository userRpository;


    public User saveTest() {

        User user = new User("email@testmail.com");
        //user.setEmail("email@testmail.com");
       return this.userRpository.save(user);

    }
}

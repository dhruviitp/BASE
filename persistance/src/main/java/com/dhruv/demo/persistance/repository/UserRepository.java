package com.dhruv.demo.persistance.repository;

import com.dhruv.demo.persistance.entity.User;

public  interface UserRepository extends BaseRepo<User,Integer> {

     User findByUserName(String userName);
     User findByUserNameAndPasswordAndDeleted(String userName, String password, byte deleted);
}

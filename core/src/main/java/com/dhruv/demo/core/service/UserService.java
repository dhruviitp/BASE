package com.dhruv.demo.core.service;

import com.dhruv.demo.helper.error.ApiException;
import com.dhruv.demo.model.User.UserDTO;
import com.dhruv.demo.model.User.UserModel;

public interface UserService {
    UserDTO getUserDetailsByUserName(String userName) throws ApiException;
    UserDTO createUser(UserDTO userDTO) throws ApiException;
    UserModel updateUser(UserModel userModel) throws ApiException;
    UserDTO deleteUser(String userName) throws ApiException;
    UserDTO verifyLogin(String userName, String password) throws ApiException;
    boolean verifyUserName(String userName);
}

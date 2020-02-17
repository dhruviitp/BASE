package com.dhruv.demo.core.service.Impl;

import com.dhruv.demo.core.covertor.UserConverter;
import com.dhruv.demo.core.service.UserService;
import com.dhruv.demo.model.User.UserModel;
import com.dhruv.demo.persistance.entity.User;
import com.dhruv.demo.helper.error.ApiException;
import com.dhruv.demo.helper.error.ErrorCode;
import com.dhruv.demo.model.User.UserDTO;
import com.dhruv.demo.persistance.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.Objects;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserConverter userConverter;

    public UserDTO getUserDetailsByUserName(String userName) throws ApiException {
        User user = userRepository.findByUserName(userName);
        if (Objects.isNull(user))
            throw new ApiException(ErrorCode.USER_NOT_FOUND);
        return userConverter.convertToModel(user);
    }

    @Transactional
    public UserDTO createUser(UserDTO userDTO) throws ApiException {
        String userName =userDTO.getUserName();
        User existingUser=userRepository.findByUserName(userName);
        if(Objects.nonNull(existingUser)){
            throw new ApiException(ErrorCode.USER_ALREADY_EXISTS);
        }
        User user = userConverter.convertToEntity(userDTO);
        user.setCreatedBy(1);
        user.setUpdatedBy(1);
        user.setDeleted((byte)0);
        user = userRepository.save(user);
        return userConverter.convertToModel(user);

    }

    public UserModel updateUser(UserModel userModel) throws ApiException {
        User user = userRepository.findByUserName(userModel.getUserName());
        if (Objects.isNull(user))
            throw new ApiException(ErrorCode.USER_NOT_FOUND);
         user.setUserName(userModel.getUserName());
         user.setContactNumber(new BigInteger(userModel.getContactNumber()));
         user.setEmail(userModel.getEmail());
         user.setName(userModel.getName());
         user.setCity(userModel.getCity());
         userRepository.save(user);
         return userModel;

    }

    @Override
    public UserDTO deleteUser(String userName) throws ApiException {
        User user = userRepository.findByUserName(userName);
        if (Objects.isNull(user))
            throw new ApiException(ErrorCode.USER_NOT_FOUND);
        user.setDeleted((byte)1);
        userRepository.save(user);
        return null;
    }

    @Override
    public UserDTO verifyLogin(String userName, String password) throws ApiException {
        User user = userRepository.findByUserNameAndPasswordAndDeleted(userName,password,(byte)0);
        if (Objects.isNull(user))
            throw new ApiException(ErrorCode.USER_NOT_FOUND);
        return null;
    }

    @Override
    public boolean verifyUserName(String userName) {
        return false;
    }
}

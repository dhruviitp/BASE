package com.dhruv.demo.core.controller;

import com.dhruv.demo.core.service.UserService;
import com.dhruv.demo.helper.error.ApiException;
import com.dhruv.demo.model.ApiResponse;
import com.dhruv.demo.model.User.UserDTO;
import com.dhruv.demo.model.User.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "users/")

public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("get")
    public ApiResponse<UserDTO> getUser(@RequestParam(value="userName")String userName) throws ApiException {
        return new ApiResponse<>(userService.getUserDetailsByUserName(userName));
    }

    @PostMapping("create")
    public ApiResponse<UserDTO> create(@RequestBody UserDTO userModel) throws ApiException {
        return new ApiResponse<>(userService.createUser(userModel));
    }

    @PutMapping("update")
    public ApiResponse<UserModel> update(@RequestBody UserModel userModel) throws ApiException {
        return new ApiResponse<>(userService.updateUser(userModel));
    }

    @GetMapping("delete")
    public ApiResponse<UserDTO>  delete(@RequestParam(value = "userName")String userName) throws ApiException{
        return new ApiResponse<>(userService.deleteUser(userName));

    }

    @GetMapping("login")
    public ApiResponse<UserDTO> login(@RequestParam(value="userName")String  userName,@RequestParam(value = "password") String password) throws ApiException {
        return new ApiResponse<>(userService.verifyLogin(userName,password));
    }

    @GetMapping("userName/validate")
    public boolean validate(@RequestParam(value="userName")String  userName) throws ApiException {
        return (userService.verifyUserName(userName));
    }


}

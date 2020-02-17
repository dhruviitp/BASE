package com.dhruv.demo.helper.error;

public enum  ErrorCode {

    USER_NOT_FOUND("User not found",500),
    USER_ALREADY_EXISTS("User Already Exists",500);
    ErrorCode(String message) {
        this.message = message;
    }

    private String message;

    public Integer getCode() {
        return code;
    }

    private Integer code;

    public String getMessage() {
        return message;
    }

    ErrorCode(String message, Integer code) {
        this.message = message;
        this.code = code;
    }

}

package com.dhruv.demo.helper.error;

public class ApiException extends Exception {


    public ApiException(ErrorCode errorCode){
        super(errorCode.getMessage());
        this.errorCode=errorCode.getCode();
        this.errorMessage=errorCode.getMessage();
    }
    public ApiException(Integer errorCode,String errorMessage){
        super(errorMessage);
        this.errorCode=errorCode;
        this.errorMessage=errorMessage;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    private String errorMessage;
    private Integer errorCode;



}

package com.example.ITBC_Logger_Endpoints.exceptions;

public class UserLoginException extends RuntimeException{

    public UserLoginException(String reason) {
        super(reason);
    }
}

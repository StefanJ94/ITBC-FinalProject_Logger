package com.example.ITBC_Logger_Endpoints.exceptions;

public class UserValidationException extends RuntimeException{

    public UserValidationException(String reason) {
        super(reason);
    }
}

package com.example.ITBC_Logger_Endpoints.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserNotFoundException extends ResponseStatusException {

    public UserNotFoundException(HttpStatus status) {
        super(status);
    }

    public UserNotFoundException(HttpStatus status, String reason) {
        super(status, reason);
    }

    public UserNotFoundException(HttpStatus status, String reason, Throwable cause) {
        super(status, reason, cause);
    }

    public UserNotFoundException(int rawStatusCode, String reason, Throwable cause) {
        super(rawStatusCode, reason, cause);
    }
}

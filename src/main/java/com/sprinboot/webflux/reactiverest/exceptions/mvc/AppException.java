package com.sprinboot.webflux.reactiverest.exceptions.mvc;

import org.springframework.http.HttpStatus;

public class AppException {
    private String message;
    private HttpStatus httpStatus;
    public AppException(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }


}

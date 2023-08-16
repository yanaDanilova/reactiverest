package com.sprinboot.webflux.reactiverest.dtos;

import org.springframework.http.HttpStatus;

import java.net.http.HttpClient;
import java.util.Date;

public class ExceptionResponse {
    private HttpStatus httpStatus;
    private String message;

    public ExceptionResponse() {
    }
    public ExceptionResponse(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

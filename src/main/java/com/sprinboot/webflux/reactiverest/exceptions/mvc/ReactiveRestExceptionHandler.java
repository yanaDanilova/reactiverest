package com.sprinboot.webflux.reactiverest.exceptions.mvc;

import com.sprinboot.webflux.reactiverest.exceptions.ReactiveRestNotFountException;
import com.sprinboot.webflux.reactiverest.exceptions.mvc.AppException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ReactiveRestExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<Object> handleReactiveNotFoundException(ReactiveRestNotFountException reactiveRestNotFountException) {
        return new ResponseEntity<>(
                new AppException(
                        reactiveRestNotFountException.getMessage(),
                        HttpStatus.NOT_FOUND),
                HttpStatus.NOT_FOUND
        );
    }
}

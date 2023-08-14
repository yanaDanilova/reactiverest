package com.sprinboot.webflux.reactiverest.exceptions;

public class ReactiveRestNotFountException extends RuntimeException{
    public ReactiveRestNotFountException(String message) {
        super(message);
    }

    public ReactiveRestNotFountException(String message, Throwable cause) {
        super(message, cause);
    }
}

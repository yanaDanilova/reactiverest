package com.sprinboot.webflux.reactiverest.exceptions;

import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Order(-2) // Установливаем порядок, чтобы этот обработчик выполнялся перед обработчиком Spring по умолчанию
public class CustomGlobalErrorHandler implements ErrorWebExceptionHandler {
    private HttpStatus httpStatus;
    private String errorMessages;
    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        if (ex instanceof ReactiveRestNotFountException) {
            ReactiveRestNotFountException reactiveRestNotFountException = (ReactiveRestNotFountException) ex;
            httpStatus = HttpStatus.NOT_FOUND;
            errorMessages = reactiveRestNotFountException.getMessage();
        }else{
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            errorMessages = "server error";
        }

        exchange.getResponse().setStatusCode(httpStatus);
        exchange.getResponse().getHeaders().setContentType(MediaType.TEXT_PLAIN);
        return exchange.getResponse().
                writeWith(Mono.just(exchange
                        .getResponse()
                        .bufferFactory()
                        .wrap(errorMessages.getBytes())));
    }
}

package com.sprinboot.webflux.reactiverest.exceptions;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sprinboot.webflux.reactiverest.dtos.ExceptionResponse;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


@Component
@Order(-2) // Установливаем порядок, чтобы этот обработчик выполнялся перед обработчиком Spring по умолчанию
public class CustomGlobalErrorHandler implements ErrorWebExceptionHandler {

    private ObjectMapper objectMapper;


    public CustomGlobalErrorHandler() {
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        if (ex instanceof Exception) {
            return handleException(exchange, ex);
        }else{
            exchange.getResponse().setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
            exchange.getResponse().getHeaders().setContentType(MediaType.TEXT_PLAIN);
            DataBuffer dataBuffer = exchange.getResponse().bufferFactory().wrap("Unknown error".getBytes());
            return exchange.getResponse().writeWith(Mono.just(dataBuffer));
        }
    }
    private DataBuffer fillDataBuffer(DataBufferFactory bufferFactory, ExceptionResponse exceptionResponse) {
        DataBuffer dataBuffer;
        try {
            dataBuffer = bufferFactory.wrap(objectMapper.writeValueAsBytes(exceptionResponse));
        } catch (JsonProcessingException e) {
            dataBuffer = bufferFactory.wrap("".getBytes());
        }
        return dataBuffer;
    }
    private ExceptionResponse generateExceptionObject(ServerWebExchange exchange, Throwable ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        if(ex instanceof ReactiveRestNotFountException){
            exceptionResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
            exceptionResponse.setMessage(ex.getMessage());
        }
        return exceptionResponse;
    }
    private Mono<Void> handleException(ServerWebExchange exchange, Throwable ex) {
        ExceptionResponse exceptionResponse = generateExceptionObject(exchange,ex);
        DataBufferFactory bufferFactory = exchange.getResponse().bufferFactory();
        DataBuffer dataBuffer = fillDataBuffer(bufferFactory,exceptionResponse);
        exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
        return exchange.getResponse().writeWith(Mono.just(dataBuffer));
    }

}

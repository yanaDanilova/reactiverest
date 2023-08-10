package com.sprinboot.webflux.reactiverest.controllers;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/home")
public class TaskScheduleController {
    @GetMapping("/index")
    @Operation(description = "greeting")
    public Mono<ResponseEntity<String>> index(){
        return Mono.just(ResponseEntity.status(HttpStatus.OK).body("Hello World"));
    }
}

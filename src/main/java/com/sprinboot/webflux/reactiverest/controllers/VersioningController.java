package com.sprinboot.webflux.reactiverest.controllers;

import com.sprinboot.webflux.reactiverest.dtos.DepartmentDto;
import com.sprinboot.webflux.reactiverest.dtos.EmployeeDto;
import com.sprinboot.webflux.reactiverest.dtos.EmployeeDtoV2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class VersioningController {

    @GetMapping("/v1")
    public Mono<EmployeeDto> getEmployeeV1 (){
        return Mono.just(new EmployeeDto(1,"Tom","devOps"));
    }

    @GetMapping("/v2")
    public Mono<EmployeeDtoV2> getEmployeeV2 (){
        return Mono.just(new EmployeeDtoV2(1,"Max",new DepartmentDto(2,"dev")));
    }

    @GetMapping(path = "/employee", params = "version=1")
    public Mono<EmployeeDto> getEmployeeParamV1 (){
        return Mono.just(new EmployeeDto(1,"Tom","devOps"));
    }
    @GetMapping(path = "/employee", params = "version=2")
    public Mono<EmployeeDtoV2> getEmployeeParamV2 (){
        return Mono.just(new EmployeeDtoV2(1,"Max",new DepartmentDto(2,"dev")));
    }

    @GetMapping(path = "/employee", headers = "version=1")
    public Mono<EmployeeDto> getEmployeeHeaderV1 (){
        return Mono.just(new EmployeeDto(1,"Tom","devOps"));
    }
    @GetMapping(path = "/employee", headers = "version=2")
    public Mono<EmployeeDtoV2> getEmployeeHeaderV2 (){
        return Mono.just(new EmployeeDtoV2(1,"Max",new DepartmentDto(2,"dev")));
    }

    @GetMapping(path = "/employee", produces = "application/vnd.company.app-v1+json")
    public Mono<EmployeeDto> getEmployeeAcceptHeaderV1 (){
        return Mono.just(new EmployeeDto(1,"Tom","devOps"));
    }
    @GetMapping(path = "/employee", produces = "application/vnd.company.app-v2+json")
    public Mono<EmployeeDtoV2> getEmployeeAcceptHeaderV2 (){
        return Mono.just(new EmployeeDtoV2(1,"Max",new DepartmentDto(2,"dev")));
    }


}

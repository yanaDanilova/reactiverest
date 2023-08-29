package com.sprinboot.webflux.reactiverest.controllers;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.sprinboot.webflux.reactiverest.dtos.DepartmentDto;
import com.sprinboot.webflux.reactiverest.dtos.EmployeeDto;
import com.sprinboot.webflux.reactiverest.dtos.EmployeeDtoV2;
import org.springframework.http.converter.json.MappingJacksonValue;
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
        return Mono.just(new EmployeeDtoV2(1,"Max",new DepartmentDto(2,"dev",3)));
    }

    @GetMapping(path = "/employee", params = "version=1")
    public Mono<EmployeeDto> getEmployeeParamV1 (){
        return Mono.just(new EmployeeDto(1,"Tom","devOps"));
    }
    @GetMapping(path = "/employee", params = "version=2")
    public Mono<EmployeeDtoV2> getEmployeeParamV2 (){
        return Mono.just(new EmployeeDtoV2(1,"Max",new DepartmentDto(2,"dev",3)));
    }

    @GetMapping(path = "/employee", headers = "version=1")
    public Mono<EmployeeDto> getEmployeeHeaderV1 (){
        return Mono.just(new EmployeeDto(1,"Tom","devOps"));
    }
    @GetMapping(path = "/employee", headers = "version=2")
    public Mono<EmployeeDtoV2> getEmployeeHeaderV2 (){
        return Mono.just(new EmployeeDtoV2(1,"Max",new DepartmentDto(2,"dev",3)));
    }

    @GetMapping(path = "/employee", produces = "application/vnd.company.app-v1+json")
    public Mono<EmployeeDto> getEmployeeAcceptHeaderV1 (){
        return Mono.just(new EmployeeDto(1,"Tom","devOps"));
    }
    @GetMapping(path = "/employee", produces = "application/vnd.company.app-v2+json")
    public Mono<EmployeeDtoV2> getEmployeeAcceptHeaderV2 (){
        return Mono.just(new EmployeeDtoV2(1,"Max",new DepartmentDto(2,"dev",3)));
    }



    @GetMapping("/department_filtering")
    public Mono<MappingJacksonValue> getDepartmentDto (){
        DepartmentDto departmentDto = new DepartmentDto(2,"dev",3);
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(departmentDto);
        SimpleBeanPropertyFilter simpleBeanPropertyFilter =SimpleBeanPropertyFilter.filterOutAllExcept("department_name","count_of_employees");
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("DepartmentDtoFilter",simpleBeanPropertyFilter);
        mappingJacksonValue.setFilters(filterProvider);
        return Mono.just(mappingJacksonValue);
    }

}

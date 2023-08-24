package com.sprinboot.webflux.reactiverest.services;

import com.sprinboot.webflux.reactiverest.dtos.EmployeeDto;
import com.sprinboot.webflux.reactiverest.entities.Employee;
import com.sprinboot.webflux.reactiverest.entities.TaskSchedule;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IEmployeeService {
    Flux<EmployeeDto> getAllEmployee();

    Mono<EmployeeDto> getEmployeeById(int id);

    Mono<Boolean> create(EmployeeDto employeeDto);

    Mono<Boolean> update(EmployeeDto employeeDto, int id);
    Mono<Boolean> deleteEmployeeById(int id);
}

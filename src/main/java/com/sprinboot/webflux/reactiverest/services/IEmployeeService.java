package com.sprinboot.webflux.reactiverest.services;

import com.sprinboot.webflux.reactiverest.entities.Employee;
import com.sprinboot.webflux.reactiverest.entities.TaskSchedule;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IEmployeeService {
    Flux<Employee> getAllEmployee();

    Mono<Employee> getEmployeeById(int id);

    Mono<Boolean> create(Employee employee);

    Mono<Boolean> update(Employee employee, int id);
    Mono<Boolean> deleteEmployeeById(int id);
}

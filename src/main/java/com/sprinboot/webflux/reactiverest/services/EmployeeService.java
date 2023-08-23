package com.sprinboot.webflux.reactiverest.services;

import com.sprinboot.webflux.reactiverest.entities.Employee;
import com.sprinboot.webflux.reactiverest.exceptions.ReactiveRestNotFountException;
import com.sprinboot.webflux.reactiverest.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
public class EmployeeService implements IEmployeeService {
    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    @Override
    public Flux<Employee> getAllEmployee() {
       return Flux.fromIterable(employeeRepository.findAll());
    }

    @Override
    public Mono<Employee> getEmployeeById(int id) {
       return Mono.fromCallable(()->employeeRepository.findById(id)
                .orElseThrow(()->new ReactiveRestNotFountException("Resource is not found")));
    }

    @Override
    public Mono<Boolean> create(Employee employee) {
        return Mono.fromCallable(()->employeeRepository.save(employee)!=null);
    }

    @Override
    public Mono<Boolean> update(Employee updatedEmployee, int id) {
        return Mono.fromCallable(() -> {
            return employeeRepository.findById(id)
                    .map(employee -> {
                        employee.setName(updatedEmployee.getName());
                        employee.setDepartment(updatedEmployee.getDepartment());
                        employeeRepository.save(employee);
                        return true;
                    })
                    .orElseThrow(() -> new ReactiveRestNotFountException("Resource is not found"));
        });
    }
    @Override
    public Mono<Boolean> deleteEmployeeById(int id) {
        employeeRepository.deleteById(id);
        return Mono.just(true);
    }
}

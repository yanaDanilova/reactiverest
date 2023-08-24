package com.sprinboot.webflux.reactiverest.services;

import com.sprinboot.webflux.reactiverest.dtos.EmployeeDto;
import com.sprinboot.webflux.reactiverest.entities.Employee;
import com.sprinboot.webflux.reactiverest.exceptions.ReactiveRestNotFountException;
import com.sprinboot.webflux.reactiverest.mappers.EmployeeMapper;
import com.sprinboot.webflux.reactiverest.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class EmployeeService implements IEmployeeService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    @Override
    public Flux<EmployeeDto> getAllEmployee() {
        return Flux.fromStream(employeeRepository.findAll().stream().map(EmployeeMapper.INSTANCE::toDto));
    }

    @Override
    public Mono<EmployeeDto> getEmployeeById(int id) {
       return Mono.just(employeeRepository.findById(id).map(EmployeeMapper.INSTANCE::toDto)
               .orElseThrow(()->new ReactiveRestNotFountException("Resource is not found")));
    }

    @Override
    public Mono<Boolean> create(EmployeeDto employeeDto) {
        return Mono.just(employeeRepository.save(EmployeeMapper.INSTANCE.toEntity(employeeDto))).hasElement();
    }

    @Override
    public Mono<Boolean> update(EmployeeDto updatedEmployeeDto, int id) {
        return Mono.just(employeeRepository.findById(id)
                .map(employee -> {
                    employee.setName(updatedEmployeeDto.getName());
                    employee.setDepartment(updatedEmployeeDto.getDepartment());
                    employeeRepository.save(employee);
                    return true;
                })
                .orElseThrow(() -> new ReactiveRestNotFountException("Resource is not found")));
    }
    @Override
    public Mono<Boolean> deleteEmployeeById(int id) {
        employeeRepository.deleteById(id);
        return Mono.just(true);
    }
}

package com.sprinboot.webflux.reactiverest.services;

import com.sprinboot.webflux.reactiverest.entities.Employee;
import com.sprinboot.webflux.reactiverest.entities.TaskSchedule;
import com.sprinboot.webflux.reactiverest.exceptions.ReactiveRestNotFountException;
import com.sprinboot.webflux.reactiverest.repositories.EmployeeRepository;
import com.sprinboot.webflux.reactiverest.repositories.TaskScheduleRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;


@Service
@Primary
public class MySQLTaskScheduleService implements ITaskScheduleService {

    private TaskScheduleRepository taskScheduleRepository;
    private EmployeeRepository employeeRepository;

    public MySQLTaskScheduleService(TaskScheduleRepository taskScheduleRepository, EmployeeRepository employeeRepository) {
        this.taskScheduleRepository = taskScheduleRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Flux<TaskSchedule> getAllTaskSchedule() {
       return Flux.fromIterable(taskScheduleRepository.findAll());
    }

    @Override
    public Mono<TaskSchedule> getTaskScheduleById(int id) {
        return Mono.fromCallable(()->taskScheduleRepository.findById(id)

                .orElseThrow(()->new ReactiveRestNotFountException("Resource is not found")));
    }

    @Override
    public Mono<TaskSchedule> create(TaskSchedule taskSchedule) {
        employeeRepository.save(taskSchedule.getEmployee());
        TaskSchedule taskSchedule1 = taskScheduleRepository.save(taskSchedule);
        return Mono.just(taskSchedule1);
    }

    @Override
    public Mono<Boolean> update(TaskSchedule updatedTaskSchedule, int id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(updatedTaskSchedule.getEmployee().getId());
        if(optionalEmployee.isEmpty()){
            Employee newEmployee = new Employee(updatedTaskSchedule.getEmployee().getName(),updatedTaskSchedule.getEmployee().getDepartment());
            employeeRepository.save(newEmployee);
        }
        taskScheduleRepository.findById(id).map(taskSchedule -> {
                    taskSchedule.setEmployee(updatedTaskSchedule.getEmployee());
                    taskSchedule.setTaskDate(updatedTaskSchedule.getTaskDate());
                    taskSchedule.setAssignedTask(updatedTaskSchedule.getAssignedTask());
                    taskSchedule.setTaskDetails(updatedTaskSchedule.getTaskDetails());
                    taskScheduleRepository.save(taskSchedule);
                    return Mono.just(true);
                })
                .orElseThrow(() -> new ReactiveRestNotFountException("Resource is not found"));
        return Mono.just(true);
    }
    @Override
    public Mono<Boolean> deleteTaskScheduleById(int id) {
        taskScheduleRepository.deleteById(id);
        return Mono.just(true);
    }
}

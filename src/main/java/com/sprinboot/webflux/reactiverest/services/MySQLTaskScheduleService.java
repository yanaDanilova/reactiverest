package com.sprinboot.webflux.reactiverest.services;

import com.sprinboot.webflux.reactiverest.dtos.TaskScheduleDto;
import com.sprinboot.webflux.reactiverest.entities.Employee;
import com.sprinboot.webflux.reactiverest.entities.TaskSchedule;
import com.sprinboot.webflux.reactiverest.exceptions.ReactiveRestNotFountException;
import com.sprinboot.webflux.reactiverest.mappers.TaskScheduleMapper;
import com.sprinboot.webflux.reactiverest.repositories.EmployeeRepository;
import com.sprinboot.webflux.reactiverest.repositories.TaskScheduleRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.stream.Collectors;


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
    public Flux<TaskScheduleDto> getAllTaskSchedule() {
       return Flux.fromIterable(taskScheduleRepository.findAll().stream().map(TaskScheduleMapper.INSTANCE::toDto).collect(Collectors.toList()));
    }

    @Override
    public Mono<TaskScheduleDto> getTaskScheduleById(int id) {
        return Mono.just(taskScheduleRepository.findById(id).map(TaskScheduleMapper.INSTANCE::toDto)
                .orElseThrow(()->new ReactiveRestNotFountException("Resource is not found")));
    }

    @Override
    public Mono<TaskScheduleDto> create(TaskScheduleDto newTaskScheduleDto) {
        TaskSchedule taskSchedule = TaskScheduleMapper.INSTANCE.toEntity(newTaskScheduleDto);
        return Mono.just(TaskScheduleMapper.INSTANCE.toDto(taskScheduleRepository.save(taskSchedule)));

    }

    @Override
    public Mono<Boolean> update(TaskScheduleDto updatedTaskScheduleDto, int id) {
        taskScheduleRepository.findById(id).map(taskSchedule -> {
                    taskSchedule.setEmployee(employeeRepository.findById(updatedTaskScheduleDto.getEmployee_id()).get());
                    taskSchedule.setTaskDate(updatedTaskScheduleDto.getTaskDate());
                    taskSchedule.setAssignedTask(updatedTaskScheduleDto.getAssignedTask());
                    taskSchedule.setTaskDetails(updatedTaskScheduleDto.getTaskDetails());
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

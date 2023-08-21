package com.sprinboot.webflux.reactiverest.services;

import com.sprinboot.webflux.reactiverest.entities.TaskSchedule;
import com.sprinboot.webflux.reactiverest.exceptions.ReactiveRestNotFountException;
import com.sprinboot.webflux.reactiverest.repositories.TaskScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
public class MySQLTaskScheduleService implements TaskScheduleService{

    private TaskScheduleRepository taskScheduleRepository;

    @Autowired
    public MySQLTaskScheduleService(TaskScheduleRepository taskScheduleRepository) {
        this.taskScheduleRepository = taskScheduleRepository;
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
    public Mono<Boolean> create(TaskSchedule taskSchedule) {
        return Mono.fromCallable(()->taskScheduleRepository.save(taskSchedule)!=null);
    }

    @Override
    public Mono<Boolean> update(TaskSchedule updatedTaskSchedule, int id) {
        return Mono.fromCallable(() -> {
            return taskScheduleRepository.findById(id)
                    .map(taskSchedule -> {
                        taskSchedule.setEmployee(updatedTaskSchedule.getEmployee());
                        taskSchedule.setTaskDate(updatedTaskSchedule.getTaskDate());
                        taskSchedule.setAssignedTask(updatedTaskSchedule.getAssignedTask());
                        taskSchedule.setTaskDetails(updatedTaskSchedule.getTaskDetails());
                        taskScheduleRepository.save(taskSchedule);
                        return true;
                    })
                    .orElseThrow(() -> new ReactiveRestNotFountException("Resource is not found"));
        });
    }
    @Override
    public Mono<Boolean> deleteTaskScheduleById(int id) {
        taskScheduleRepository.deleteById(id);
        return Mono.just(true);
    }
}

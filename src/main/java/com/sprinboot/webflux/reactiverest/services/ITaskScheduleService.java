package com.sprinboot.webflux.reactiverest.services;

import com.sprinboot.webflux.reactiverest.entities.TaskSchedule;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ITaskScheduleService {
    Flux<TaskSchedule> getAllTaskSchedule();

    Mono<TaskSchedule> getTaskScheduleById(int id);

    Mono<TaskSchedule> create(TaskSchedule taskSchedule);

    Mono<Boolean> update(TaskSchedule updatedTaskSchedule, int id);
    Mono<Boolean> deleteTaskScheduleById(int id);
}

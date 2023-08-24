package com.sprinboot.webflux.reactiverest.services;

import com.sprinboot.webflux.reactiverest.dtos.TaskScheduleDto;
import com.sprinboot.webflux.reactiverest.entities.TaskSchedule;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ITaskScheduleService {
    Flux<TaskScheduleDto> getAllTaskSchedule();

    Mono<TaskScheduleDto> getTaskScheduleById(int id);

    Mono<TaskScheduleDto> create(TaskScheduleDto taskScheduleDto);

    Mono<Boolean> update(TaskScheduleDto updatedTaskScheduleDto, int id);
    Mono<Boolean> deleteTaskScheduleById(int id);
}

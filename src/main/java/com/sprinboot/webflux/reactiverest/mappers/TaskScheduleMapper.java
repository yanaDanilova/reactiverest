package com.sprinboot.webflux.reactiverest.mappers;

import com.sprinboot.webflux.reactiverest.dtos.TaskScheduleDto;
import com.sprinboot.webflux.reactiverest.entities.TaskSchedule;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TaskScheduleMapper {
    TaskScheduleMapper INSTANCE = Mappers.getMapper(TaskScheduleMapper.class);

    TaskSchedule toEntity(TaskScheduleDto taskScheduleDto);
    TaskScheduleDto toDto(TaskSchedule taskSchedule);
}

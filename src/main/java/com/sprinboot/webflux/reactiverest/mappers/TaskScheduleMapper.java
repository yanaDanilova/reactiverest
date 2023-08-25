package com.sprinboot.webflux.reactiverest.mappers;

import com.sprinboot.webflux.reactiverest.dtos.TaskScheduleDto;
import com.sprinboot.webflux.reactiverest.entities.Employee;
import com.sprinboot.webflux.reactiverest.entities.TaskSchedule;
import com.sprinboot.webflux.reactiverest.repositories.EmployeeRepository;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TaskScheduleMapper {

    TaskScheduleMapper INSTANCE = Mappers.getMapper(TaskScheduleMapper.class);


    @Mapping(target = "employee", ignore = true)
    TaskSchedule toEntity(TaskScheduleDto taskScheduleDto, @Context EmployeeRepository employeeRepository);


    @AfterMapping
    default void mapEmployee(TaskScheduleDto taskScheduleDto, @MappingTarget TaskSchedule taskSchedule,@Context EmployeeRepository employeeRepository) {
        if (taskScheduleDto.getEmployee_id() != 0) {
            Employee employee = employeeRepository.findById(taskScheduleDto.getEmployee_id()).orElse(null);
            if (employee != null) {
                taskSchedule.setEmployee(employee);
            } else {
                throw new IllegalArgumentException("Employee with ID " + taskScheduleDto.getEmployee_id() + " not found.");
            }
        }
    }

    @Mapping(source = "employee.id", target = "employee_id")
    TaskScheduleDto toDto(TaskSchedule taskSchedule);

    @AfterMapping
    default void mapEmployeeId(TaskSchedule taskSchedule, @MappingTarget TaskScheduleDto taskScheduleDto) {
        if (taskSchedule.getEmployee() != null) {
            taskScheduleDto.setEmployee_id(taskSchedule.getEmployee().getId());
        }
    }
}

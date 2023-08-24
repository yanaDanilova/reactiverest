package com.sprinboot.webflux.reactiverest.mappers;

import com.sprinboot.webflux.reactiverest.dtos.EmployeeDto;
import com.sprinboot.webflux.reactiverest.entities.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EmployeeMapper {

    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    Employee toEntity(EmployeeDto employeeDto);
    EmployeeDto toDto(Employee employee);
}

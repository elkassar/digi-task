package com.digi.task.mapper;

import com.digi.task.dto.EmployeeRequestDto;
import com.digi.task.dto.EmployeeResponseDto;
import com.digi.task.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    Employee toEntity(EmployeeRequestDto employeeRequestDto);
    EmployeeResponseDto toDto(Employee employee);
    Iterable<EmployeeResponseDto> toDtoList(Iterable<Employee> employees);
}

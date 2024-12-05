package com.digi.task.service;

import com.digi.task.dto.EmployeeRequestDto;
import com.digi.task.dto.EmployeeResponseDto;

public interface EmployeeService {

    void createEmployee(EmployeeRequestDto employeeRequestDto);
    EmployeeResponseDto getEmployee(String employeeId);
    void updateEmployee(String employeeId, EmployeeRequestDto employeeRequestDto);
    void deleteEmployee(String employeeId);
    Iterable<EmployeeResponseDto> getAllEmployees();
}

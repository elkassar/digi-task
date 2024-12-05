package com.digi.task.service;

import com.digi.task.dto.EmployeeRequestDto;
import com.digi.task.dto.EmployeeResponseDto;
import com.digi.task.entity.Employee;
import com.digi.task.exception.EmployeeNotFoundException;
import com.digi.task.externalservice.DepartmentVerificationService;
import com.digi.task.externalservice.EmailNotificationService;
import com.digi.task.externalservice.EmailValidationService;
import com.digi.task.mapper.EmployeeMapper;
import com.digi.task.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final EmailValidationService emailValidationService;
    private final DepartmentVerificationService departmentVerificationService;
    private final EmailNotificationService emailNotificationService;

    @Override
    public void createEmployee(EmployeeRequestDto employeeRequestDto) {
        emailValidationService.checkEmailValidity(employeeRequestDto.getEmail());
        departmentVerificationService.verifyDepartment(employeeRequestDto.getDepartment());
        employeeRepository.save(employeeMapper.toEntity(employeeRequestDto));
        emailNotificationService.sendEmailNotification(employeeRequestDto.getEmail());
    }

    @Override
    public EmployeeResponseDto getEmployee(String employeeId) {
        return employeeRepository.findById(employeeId)
                .map(employeeMapper::toDto)
                .orElseThrow(EmployeeNotFoundException::new);
    }

    @Override
    public void updateEmployee(String employeeId, EmployeeRequestDto employeeRequestDto) {
        employeeRepository.findById(employeeId).ifPresentOrElse(
                empl -> {
                    emailValidationService.checkEmailValidity(employeeRequestDto.getEmail());
                    departmentVerificationService.verifyDepartment(employeeRequestDto.getDepartment());
                    employeeRequestDto.setId(employeeId);
                    Employee employee = employeeMapper.toEntity(employeeRequestDto);
                    employeeRepository.save(employee);
                    if (!employeeRequestDto.getEmail().equalsIgnoreCase(empl.getEmail())) {
                        emailNotificationService.sendEmailNotification(employeeRequestDto.getEmail());
                    }
                },
                () -> {throw new EmployeeNotFoundException();});
    }

    @Override
    public void deleteEmployee(String employeeId) {
        employeeRepository.findById(employeeId).ifPresentOrElse(
                e -> employeeRepository.deleteById(employeeId),
                () -> {throw new EmployeeNotFoundException();});
    }

    @Override
    public Iterable<EmployeeResponseDto> getAllEmployees() {
        return employeeMapper.toDtoList(employeeRepository.findAll());
    }
}

package com.digi.task.controller;

import com.digi.task.TaskApplication;
import com.digi.task.dto.EmployeeRequestDto;
import com.digi.task.dto.EmployeeResponseDto;
import com.digi.task.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<Object> createEmployee(@Valid @RequestBody EmployeeRequestDto employeeRequestDto) {
        log.info("Started to create an employee");
        employeeService.createEmployee(employeeRequestDto);
        log.info("Ended successfully to create an employee");
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("{employeeId}")
    public ResponseEntity<EmployeeResponseDto> getEmployeeById(@PathVariable("employeeId") String employeeId) {
        log.info("Started to get an employee by id: {}", employeeId);
        EmployeeResponseDto employeeResponseDto = employeeService.getEmployee(employeeId);
        log.info("Ended successfully to get an employee by id: {}", employeeId);
        return ResponseEntity.status(HttpStatus.OK).body(employeeResponseDto);
    }

    @PutMapping("{employeeId}")
    public ResponseEntity<Object> updateEmployee(@PathVariable("employeeId") String employeeId,
                                                 @Valid @RequestBody EmployeeRequestDto employeeRequestDto) {
        log.info("Started to update an employee by id: {}", employeeId);
        employeeService.updateEmployee(employeeId, employeeRequestDto);
        log.info("Ended successfully to update employee by id: {}", employeeId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("{employeeId}")
    public ResponseEntity<Object> deleteEmployee(@PathVariable("employeeId") String employeeId) {
        log.info("Started to delete an employee by id: {}", employeeId);
        employeeService.deleteEmployee(employeeId);
        log.info("Ended successfully to delete an employee by id: {}", employeeId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping
    public ResponseEntity<Iterable<EmployeeResponseDto>> getAllEmployees() {
        log.info("Started to list all employees");
        Iterable<EmployeeResponseDto> employeeResponseDtos = employeeService.getAllEmployees();
        log.info("Ended successfully to list all employees");
        return ResponseEntity.status(HttpStatus.OK).body(employeeResponseDtos);
    }
}

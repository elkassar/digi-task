package com.digi.task.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequestDto implements Serializable {

    private String id;
    @NotBlank(message = "First Name is Required!")
    private String firstName;
    @NotBlank(message = "Last Name is Required!")
    private String lastName;
    @Email(message = "Email Format is Invalid!", regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")
    private String email;
    @NotBlank(message = "Employee must be assigned to a department")
    private String department;
    @Positive(message = "Salary must be positive!")
    private Double salary;
}

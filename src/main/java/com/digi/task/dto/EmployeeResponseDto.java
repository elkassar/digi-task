package com.digi.task.dto;

import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class EmployeeResponseDto implements Serializable {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String department;
    private Double salary;
}

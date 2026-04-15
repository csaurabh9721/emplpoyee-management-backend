package com.devix.employemanagement.dtos.EmployeeDto;

import com.devix.employemanagement.entities.User.Employee;
import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeEmergencyContactDto {
    private Long id;
    private String name;
    private String phone;
    private String relation;
}

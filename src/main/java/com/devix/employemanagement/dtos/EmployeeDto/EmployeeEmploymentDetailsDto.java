package com.devix.employemanagement.dtos.EmployeeDto;

import com.devix.employemanagement.entities.User.Employee;
import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeEmploymentDetailsDto {
    private Long id;
    private String pfNumber;
    private String esiNumber;
}

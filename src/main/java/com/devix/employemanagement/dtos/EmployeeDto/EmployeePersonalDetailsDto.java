package com.devix.employemanagement.dtos.EmployeeDto;

import com.devix.employemanagement.entities.User.Employee;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeePersonalDetailsDto {
    private Long id;
    private String personalEmail;
    private String alternateMobileNumber;
    private LocalDate dateOfBirth;
    private String gender;
    private String maritalStatus;
    private String bloodGroup;
    private String panNumber;
    private String aadharNumber;
}

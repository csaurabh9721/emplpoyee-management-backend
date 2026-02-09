package com.devix.employemanagement.dtos.EmployeeDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequestDto {

    private Long userId;
    private Long organizationId;
    private String employeeCode;
    private String fullName;
    private String designation;
    private String department;
    private String profileImageUrl;
    private Long primaryOfficeId;
    private LocalDate joiningDate;
    private String status;
}
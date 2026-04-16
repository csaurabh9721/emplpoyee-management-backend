package com.devix.employemanagement.dtos.EmployeeDto.requestDto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PersonalRequestDto {
    private LocalDate dateOfBirth;
    private String gender;
    private String maritalStatus;
    private String bloodGroup;
    private String panNumber;
    private String aadharNumber;
    private String personalEmail;
    private String alternateMobileNumber;
}

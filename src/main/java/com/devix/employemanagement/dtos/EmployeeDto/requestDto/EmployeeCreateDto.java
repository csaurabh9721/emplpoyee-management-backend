package com.devix.employemanagement.dtos.EmployeeDto.requestDto;

import lombok.*;

import java.time.LocalDate;

@Data
public class EmployeeCreateDto {

    // 🔗 Required Links
    private Long userId;
    private Long organizationId;
    private Long designationId;
    private Long primaryOfficeId;

    // 👤 Basic Info
    private String employeeCode;
    private String fullName;
    private String department;
    private String employmentType; // FULL_TIME / CONTRACT

    private LocalDate joiningDate;
    private Long managerId;
    private String managerName;

    // 👤 Personal
    private PersonalRequestDto personal;

    // 🏠 Address
    private AddressRequestDto address;

    // 🚨 Emergency
    private EmergencyRequestDto emergency;

    // 🏦 Bank
    private BankRequestDto bank;

    // 💼 Employment
    private EmploymentRequestDto employment;
}
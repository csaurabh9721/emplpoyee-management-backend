package com.devix.employemanagement.dtos.EmployeeDto;

import com.devix.employemanagement.utils.enums.GenderEnum;
import com.devix.employemanagement.utils.enums.MaritalStatusEnum;
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

    private Long organizationId;
    private String employeeCode;
    private String fullName;
    private Long designationId;
    private String department;
    private String profileImageUrl;
    private Long primaryOfficeId;
    private LocalDate joiningDate;
    private String status;

    // User
    private Long userId;
    private String email;
    private String phone;

    // Employee
    private String firstName;
    private String lastName;

    // Personal
    private LocalDate dateOfBirth;
    private GenderEnum gender;
    private MaritalStatusEnum maritalStatus;
    private String bloodGroup;
    private String panNumber;
    private String aadharNumber;

    // Address
    private String address;
    private String city;
    private String state;
    private String postalCode;
    private String country;

    // Emergency
    private String emergencyName;
    private String emergencyPhone;
    private String emergencyRelation;

    // Bank
    private String bankName;
    private String accountNumber;
    private String ifscCode;

    // Employment
    private String pfNumber;
    private String esiNumber;
}
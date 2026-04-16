package com.devix.employemanagement.dtos.EmployeeDto;

import lombok.Data;

@Data
public class EmployeeUpdateProfileDto {

    private Long employeeId;

    // 👤 Personal
    private String fullName;
    private String personalEmail;
    private String alternateMobileNumber;
    private String gender;
    private String maritalStatus;
    private String bloodGroup;

    // 🏠 Current Address
    private String address;
    private String city;
    private String state;
    private String postalCode;
    private String country;

    // 🏠 Permanent Address
    private String permanentAddress;
    private String permanentCity;
    private String permanentState;
    private String permanentPostalCode;
    private String permanentCountry;

    // 🚨 Emergency
    private String emergencyName;
    private String emergencyPhone;
    private String emergencyRelation;
}

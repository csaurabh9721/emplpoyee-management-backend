package com.devix.employemanagement.dtos.EmployeeDto;

import com.devix.employemanagement.entities.User.Employee;
import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeAddressDto {
    private Long id;
    private String address;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    private String permanentAddress;
    private String permanentCity;
    private String permanentState;
    private String permanentPostalCode;
    private String permanentCountry;
}
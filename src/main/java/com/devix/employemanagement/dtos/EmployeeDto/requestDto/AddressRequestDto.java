package com.devix.employemanagement.dtos.EmployeeDto.requestDto;

import lombok.Data;

@Data
public class AddressRequestDto {
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

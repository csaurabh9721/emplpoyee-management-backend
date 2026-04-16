package com.devix.employemanagement.dtos.EmployeeDto.requestDto;

import lombok.Data;

@Data
public class EmergencyRequestDto {
    private String name;
    private String phone;
    private String relation;
}

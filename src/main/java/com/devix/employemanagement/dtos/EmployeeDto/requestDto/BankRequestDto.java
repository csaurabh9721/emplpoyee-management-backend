package com.devix.employemanagement.dtos.EmployeeDto.requestDto;

import lombok.Data;

@Data
public class BankRequestDto {
    private String bankName;
    private String accountNumber;
    private String ifscCode;
}

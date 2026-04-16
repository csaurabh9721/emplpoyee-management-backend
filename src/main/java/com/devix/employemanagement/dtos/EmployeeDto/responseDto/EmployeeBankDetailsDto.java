package com.devix.employemanagement.dtos.EmployeeDto.responseDto;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeBankDetailsDto {
    private Long id;
    private String bankName;
    private String accountNumber;
    private String ifscCode;
}

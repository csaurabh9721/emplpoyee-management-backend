package com.devix.employemanagement.dtos.EmployeeDto;

import com.devix.employemanagement.entities.User.Employee;
import jakarta.persistence.*;
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

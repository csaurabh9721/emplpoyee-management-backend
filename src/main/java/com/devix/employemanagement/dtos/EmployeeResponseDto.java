package com.devix.employemanagement.dtos;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponseDto {

    private Long id;
    private String employeeCode;
    private String fullName;
    private String designation;
    private String department;
    private String profileImageUrl;
    private String status;
}

package com.devix.employemanagement.dtos.EmployeeDto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
    private LocalDate joiningDate;
    private String status;
    private Long userId;
    private Long organizationId;
    private Long primaryOfficeId;
    private LocalDateTime createdAt;
}

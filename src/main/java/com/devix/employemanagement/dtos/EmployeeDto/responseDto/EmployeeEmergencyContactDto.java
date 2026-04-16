package com.devix.employemanagement.dtos.EmployeeDto.responseDto;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeEmergencyContactDto {
    private Long id;
    private String name;
    private String phone;
    private String relation;
}

package com.devix.employemanagement.dtos.EmployeeDto.responseDto;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeEmploymentDetailsDto {
    private Long id;
    private String pfNumber;
    private String esiNumber;
}

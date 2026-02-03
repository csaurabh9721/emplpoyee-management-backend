package com.devix.employemanagement.dtos;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationResponseDto {

    private Long id;
    private String name;
    private String code;
    private String timezone;
    private Boolean active;
}

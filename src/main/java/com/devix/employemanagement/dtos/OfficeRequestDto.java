package com.devix.employemanagement.dtos;

import com.devix.employemanagement.entities.Organization;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OfficeRequestDto {

    private Long organizationId;
    private String name;
    private Double latitude;
    private Double longitude;
    private String address;
    private Boolean active;
}

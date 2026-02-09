package com.devix.employemanagement.dtos.officeDto;

import com.devix.employemanagement.entities.Organization;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OfficeResponseDto {

    private Long id;
    private String name;
    private Organization organization;
    private Double latitude;
    private Double longitude;
    private String address;
    private Boolean active;
}

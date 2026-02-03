package com.devix.employemanagement.dtos;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OfficeResponseDto {

    private Long id;
    private String name;
    private Double latitude;
    private Double longitude;
    private Integer geofenceRadiusM;
    private Boolean active;
}

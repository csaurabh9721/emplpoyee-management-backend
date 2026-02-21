package com.devix.employemanagement.dtos.holidayDtos;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class HolidayResponseDTO {

    private Long id;
    private String name;
    private LocalDate date;
    private boolean optionalHoliday;
    private Long officeId;
    private String officeName;
}

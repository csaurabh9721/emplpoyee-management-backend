package com.devix.employemanagement.dtos.holidayDtos;

import lombok.Data;
import java.time.LocalDate;

@Data
public class HolidayRequestDTO {

    private String name;
    private LocalDate date;
    private boolean optionalHoliday;
    private Long officeId;
}
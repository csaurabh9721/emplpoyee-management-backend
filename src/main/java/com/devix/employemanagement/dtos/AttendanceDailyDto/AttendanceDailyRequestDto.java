package com.devix.employemanagement.dtos.AttendanceDailyDto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class AttendanceDailyRequestDto {
    private Long employeeId;
    private Long organizationId;
}

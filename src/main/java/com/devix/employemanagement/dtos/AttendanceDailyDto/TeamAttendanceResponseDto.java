package com.devix.employemanagement.dtos.AttendanceDailyDto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TeamAttendanceResponseDto {

    private Long employeeId;
    private String employeeName;
    private String employeeCode;
    private List<AttendanceDailyResponseDto> attendance;
}
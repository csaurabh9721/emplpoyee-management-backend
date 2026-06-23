package com.devix.employemanagement.dtos.AttendanceDailyDto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class TeamAttendanceResponseDto {
    private LocalDate attendanceDate;
    private List<TeamAttendanceListResponseDto> attendanceList;
}
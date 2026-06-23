package com.devix.employemanagement.services.AttendanceService;

import com.devix.employemanagement.dtos.AttendanceDailyDto.AttendanceDailyRequestDto;
import com.devix.employemanagement.dtos.AttendanceDailyDto.AttendanceDailyResponseDto;
import com.devix.employemanagement.dtos.AttendanceDailyDto.TeamAttendanceResponseDto;

import java.time.LocalDate;
import java.util.List;

public interface IAttendanceDailyService {

    AttendanceDailyResponseDto markAttendance(AttendanceDailyRequestDto request);

    List<AttendanceDailyResponseDto> getAttendanceByEmployeeAndDateRange(
            Long employeeId,
            LocalDate startDate,
            LocalDate endDate);

    List<TeamAttendanceResponseDto> getTeamAttendance(
            Long managerId,
            LocalDate startDate,
            LocalDate endDate);
}

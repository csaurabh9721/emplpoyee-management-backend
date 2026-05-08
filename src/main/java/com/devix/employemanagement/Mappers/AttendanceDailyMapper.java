package com.devix.employemanagement.Mappers;

import com.devix.employemanagement.dtos.AttendanceDailyDto.AttendanceDailyResponseDto;
import com.devix.employemanagement.dtos.AttendanceDailyDto.TeamAttendanceListResponseDto;
import com.devix.employemanagement.entities.AttendanceDaily;
import com.devix.employemanagement.entities.User.Employee;

import java.time.Duration;

public class AttendanceDailyMapper {

    public static AttendanceDailyResponseDto toDto(AttendanceDaily entity) {
        String formatted = "";
        if (entity.getPunchInTime() != null &&entity.getPunchOutTime() != null) {
          long getInMinutes =  Duration.between(entity.getPunchInTime(), entity.getPunchOutTime()).toMinutes();
            long hours = getInMinutes / 60;
            long minutes = getInMinutes % 60;
            formatted = hours + "h " + minutes + "m";
        }
        return AttendanceDailyResponseDto.builder()
                .id(entity.getId())
                .employeeId(entity.getEmployee().getId())
                .organizationId(entity.getOrganization().getId())
                .attendanceDate(entity.getAttendanceDate())
                .punchInTime(entity.getPunchInTime())
                .punchOutTime(entity.getPunchOutTime())
                .workHour(formatted)
                .status(entity.getStatus())
                .build();
    }

    public static TeamAttendanceListResponseDto toTeamAttendanceListResponseDto(Employee emp, AttendanceDailyResponseDto entity) {
        return TeamAttendanceListResponseDto.builder()
                .id(entity.getId())
                .employeeId(emp.getId())
                .employeeName(emp.getFullName())
                .organizationId(emp.getOrganization().getId())
                .attendanceDate(entity.getAttendanceDate())
                .punchInTime(entity.getPunchInTime())
                .punchOutTime(entity.getPunchOutTime())
                .workHour(entity.getWorkHour())
                .status(entity.getStatus())
                .build();
    }
}

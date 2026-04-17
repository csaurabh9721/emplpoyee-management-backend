package com.devix.employemanagement.Mappers.dashboardMapper;

import com.devix.employemanagement.dtos.dashboardDto.AnnouncementDto;
import com.devix.employemanagement.dtos.dashboardDto.TodayAttendanceDto;
import com.devix.employemanagement.entities.AttendanceDaily;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class DashboardMapper {

    public TodayAttendanceDto mapTodayAttendance(AttendanceDaily attendance) {

        if (attendance == null) return null;
        String formatted = "";

        if (attendance.getPunchInTime() != null && attendance.getPunchOutTime() != null) {
            long getInMinutes =  Duration.between(attendance.getPunchInTime(), attendance.getPunchOutTime()).toMinutes();
            long hours = getInMinutes / 60;
            long minutes = getInMinutes % 60;
            formatted = hours + "h " + minutes + "m";
        }
        return TodayAttendanceDto.builder()
                .attendanceDate(attendance.getAttendanceDate())
                .punchInTime(attendance.getPunchInTime())
                .punchOutTime(attendance.getPunchOutTime())
                .workHour(formatted)
                .build();
    }
}

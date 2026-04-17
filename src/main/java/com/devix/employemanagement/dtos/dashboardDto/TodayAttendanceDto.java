package com.devix.employemanagement.dtos.dashboardDto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TodayAttendanceDto {

    private LocalDate attendanceDate;
    private LocalDateTime punchInTime;
    private LocalDateTime punchOutTime;
    private String workHour;
}

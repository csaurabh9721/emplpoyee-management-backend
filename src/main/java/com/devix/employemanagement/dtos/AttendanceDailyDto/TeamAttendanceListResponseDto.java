package com.devix.employemanagement.dtos.AttendanceDailyDto;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeamAttendanceListResponseDto {
    private Long id;
    private Long employeeId;
    private String employeeName;
    private Long organizationId;
    private LocalDate attendanceDate;
    private LocalDateTime punchInTime;
    private LocalDateTime punchOutTime;
    private String status;
    private String workHour;
}

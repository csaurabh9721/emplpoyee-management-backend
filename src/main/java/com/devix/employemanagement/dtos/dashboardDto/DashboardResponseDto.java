package com.devix.employemanagement.dtos.dashboardDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DashboardResponseDto {

    private Long employeeId;
    private Long organizationId;
    private Long officeId;

    private String employeeName;
    private String designationName;
    private String image;

    private TodayAttendanceDto todayAttendance;

    private List<AnnouncementDto> announcements;
}

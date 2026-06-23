package com.devix.employemanagement.services.dashboardService;

import com.devix.employemanagement.Mappers.dashboardMapper.DashboardMapper;
import com.devix.employemanagement.dtos.dashboardDto.DashboardResponseDto;
import com.devix.employemanagement.dtos.dashboardDto.TodayAttendanceDto;
import com.devix.employemanagement.entities.AttendanceDaily;
import com.devix.employemanagement.entities.User.Employee;
import com.devix.employemanagement.repo.AttendanceDailyRepository;
import com.devix.employemanagement.repo.userRepo.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class DashboardService implements IDashboardService {

    private final EmployeeRepository employeeRepository;
    private final AttendanceDailyRepository attendanceRepository;
    private final DashboardMapper mapper;

    @Override
    public DashboardResponseDto getDashboard(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new RuntimeException("Employee not found"));

        Long orgId = employee.getOrganization().getId();
        Long officeId = employee.getPrimaryOffice().getId();
        AttendanceDaily attendance = attendanceRepository
                .findByEmployeeIdAndAttendanceDate(employeeId, LocalDate.now())
                .orElse(null);
        TodayAttendanceDto todayAttendance;
        if (attendance != null) {
            todayAttendance = mapper.mapTodayAttendance(attendance);
        } else {
            todayAttendance = new TodayAttendanceDto(
                    LocalDate.now(), null, null, null
            );
        }


        return DashboardResponseDto.builder()
                .employeeId(employee.getId())
                .organizationId(orgId)
                .officeId(officeId)
                .employeeName(employee.getFullName())
                .designationName(employee.getDesignation().getDescription())
                .image(employee.getProfileImageUrl()) // adjust field name
                .todayAttendance(todayAttendance)
                .announcements(new ArrayList<>())
                .build();
    }
}

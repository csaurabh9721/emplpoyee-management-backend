package com.devix.employemanagement.services.AttendanceService;

import com.devix.employemanagement.Mappers.AttendanceDailyMapper;
import com.devix.employemanagement.dtos.AttendanceDailyDto.AttendanceDailyRequestDto;
import com.devix.employemanagement.dtos.AttendanceDailyDto.AttendanceDailyResponseDto;
import com.devix.employemanagement.entities.AttendanceDaily;
import com.devix.employemanagement.entities.Employee;
import com.devix.employemanagement.entities.Organization;
import com.devix.employemanagement.repo.AttendanceDailyRepository;
import com.devix.employemanagement.repo.EmployeeRepository;
import com.devix.employemanagement.repo.OrganizationRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendanceDailyService {

    private final AttendanceDailyRepository attendanceRepo;
    private final EmployeeRepository employeeRepo;
    private final OrganizationRepo organizationRepo;

    @Transactional
    public AttendanceDailyResponseDto markAttendance(AttendanceDailyRequestDto request) {

        Employee employee = employeeRepo.findById(request.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        Organization organization = organizationRepo.findById(request.getOrganizationId())
                .orElseThrow(() -> new RuntimeException("Organization not found"));

        LocalDate today = LocalDate.now();
        LocalDateTime now = LocalDateTime.now();

        AttendanceDaily attendance = attendanceRepo
                .findByEmployeeIdAndAttendanceDate(employee.getId(), today)
                .orElse(null);

        if (attendance == null) {
            attendance = new AttendanceDaily();
            attendance.setEmployee(employee);
            attendance.setOrganization(organization);
            attendance.setAttendanceDate(today);
            attendance.setPunchInTime(now);
        } else {
            // ✅ Any further punch → Update Punch Out
            attendance.setPunchOutTime(now);
        }

        AttendanceDaily saved = attendanceRepo.save(attendance);

        return AttendanceDailyMapper.toDto(saved);
    }


    public List<AttendanceDailyResponseDto> getAttendanceByEmployee(Long employeeId) {

        return attendanceRepo.findByEmployeeId(employeeId)
                .stream()
                .map(AttendanceDailyMapper::toDto)
                .toList();
    }
}

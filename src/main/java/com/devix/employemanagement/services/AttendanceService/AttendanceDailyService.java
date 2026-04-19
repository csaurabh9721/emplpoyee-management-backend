package com.devix.employemanagement.services.AttendanceService;

import com.devix.employemanagement.Mappers.AttendanceDailyMapper;
import com.devix.employemanagement.dtos.AttendanceDailyDto.AttendanceDailyRequestDto;
import com.devix.employemanagement.dtos.AttendanceDailyDto.AttendanceDailyResponseDto;
import com.devix.employemanagement.entities.AttendanceDaily;
import com.devix.employemanagement.entities.Holiday;
import com.devix.employemanagement.entities.Organization;
import com.devix.employemanagement.entities.User.Employee;
import com.devix.employemanagement.repo.AttendanceDailyRepository;
import com.devix.employemanagement.repo.HolidayYearRepository;
import com.devix.employemanagement.repo.OrganizationRepo;
import com.devix.employemanagement.repo.userRepo.EmployeeRepository;
import com.devix.employemanagement.utils.enums.AttendanceStatus;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AttendanceDailyService {

    private final AttendanceDailyRepository attendanceRepo;
    private final EmployeeRepository employeeRepo;
    private final OrganizationRepo organizationRepo;
    private final HolidayYearRepository holidayYearRepository;

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
            attendance.setStatus(AttendanceStatus.PRESENT.toString());
        } else {
            // ✅ Any further punch → Update Punch Out
            attendance.setPunchOutTime(now);
            if (attendance.getPunchInTime() != null && attendance.getPunchOutTime() != null) {

                long totalMinutes = Duration
                        .between(attendance.getPunchInTime(), attendance.getPunchOutTime())
                        .toMinutes();
                long fullDay = 8 * 60;          // 480
                long lateThreshold = 7 * 60 + 45; // 465
                long halfDayThreshold = 3 * 60 + 30; // 210
                if (totalMinutes < halfDayThreshold) {
                    attendance.setStatus(AttendanceStatus.ABSENT.name());
                } else if (totalMinutes < lateThreshold) {
                    attendance.setStatus(AttendanceStatus.HALF_DAY.name());
                } else if (totalMinutes < fullDay) {
                    attendance.setStatus(AttendanceStatus.LATE.name());
                } else {
                    attendance.setStatus(AttendanceStatus.PRESENT.name());
                }
            }

        }

        AttendanceDaily saved = attendanceRepo.save(attendance);

        return AttendanceDailyMapper.toDto(saved);
    }


    public List<AttendanceDailyResponseDto> getAttendanceByEmployeeAndDateRange(
            Long employeeId,
            LocalDate startDate,
            LocalDate endDate) {

        // 1. DB se attendance data
        List<AttendanceDaily> attendanceList =
                attendanceRepo.findByEmployeeIdAndAttendanceDateBetween(
                        employeeId, startDate, endDate);

        Map<LocalDate, AttendanceDaily> attendanceMap =
                attendanceList.stream()
                        .collect(Collectors.toMap(
                                AttendanceDaily::getAttendanceDate,
                                a -> a
                        ));

        // 2. Holidays fetch
        List<Holiday> holidays =
                holidayYearRepository.findByDateBetween(startDate, endDate);

        Set<LocalDate> holidayDates =
                holidays.stream()
                        .map(Holiday::getDate)
                        .collect(Collectors.toSet());

        // 3. Loop all dates
        List<AttendanceDailyResponseDto> result = new ArrayList<>();

        LocalDate current = startDate;

        while (!current.isAfter(endDate)) {

            AttendanceDailyResponseDto dto = new AttendanceDailyResponseDto();
            dto.setAttendanceDate(current);

            // Case 1: Attendance exists
            if (attendanceMap.containsKey(current)) {

                AttendanceDaily att = attendanceMap.get(current);
                dto = AttendanceDailyMapper.toDto(att);

            } else {

                // Case 2: Holiday
                if (holidayDates.contains(current)) {
                    dto.setStatus(AttendanceStatus.HOLIDAY.name());
                }

                // Case 3: Weekly Off (Saturday, Sunday)
                else if (current.getDayOfWeek() == DayOfWeek.SUNDAY || current.getDayOfWeek() == DayOfWeek.SATURDAY) {
                    dto.setStatus(AttendanceStatus.WEEK_OFF.name());
                }

                // Case 4: Absent
                else {
                    dto.setStatus(AttendanceStatus.ABSENT.name());
                }
            }

            result.add(dto);
            current = current.plusDays(1);
        }

        return result.reversed();
    }
}

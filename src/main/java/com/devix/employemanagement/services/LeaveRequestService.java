package com.devix.employemanagement.services;

import com.devix.employemanagement.Mappers.LeaveRequestMapper;
import com.devix.employemanagement.dtos.holidayDtos.HolidayResponseDTO;
import com.devix.employemanagement.dtos.leaveDtos.LeaveRequestCreateDto;
import com.devix.employemanagement.dtos.leaveDtos.LeaveRequestResponseDto;
import com.devix.employemanagement.entities.LeaveRequest;
import com.devix.employemanagement.entities.User.Employee;
import com.devix.employemanagement.exceptions.BadRequestException;
import com.devix.employemanagement.repo.LeaveRequestRepository;
import com.devix.employemanagement.repo.userRepo.EmployeeRepository;
import com.devix.employemanagement.services.holidayService.HolidayService;
import com.devix.employemanagement.utils.enums.LeaveStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LeaveRequestService {

    private final LeaveRequestRepository leaveRepository;
    private final EmployeeRepository employeeRepository;
    private final LeaveRequestMapper mapper;
    private final HolidayService holidayService;



    /* ================= GET ALL ================= */
    public List<LeaveRequestResponseDto> getAllLeaves() {
        return leaveRepository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    /* ================= APPROVE / REJECT ================= */
    public LeaveRequestResponseDto updateStatus(Long id, LeaveStatus status) {

        LeaveRequest leave = leaveRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Leave not found"));

        leave.setStatus(status);
        leave.setActionedAt(LocalDateTime.now());

        leave = leaveRepository.save(leave);

        return mapper.toDto(leave);
    }

    /* ================= APPLY LEAVE ================= */
    public LeaveRequestResponseDto applyLeave(LeaveRequestCreateDto dto) {

        Employee employee = employeeRepository.findById(dto.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        Set<LocalDate> holidays = getHolidayDates(employee.getPrimaryOffice().getId()); // implement this

        validateLeaveDates(dto.getStartDate(), dto.getEndDate(), holidays);

        LeaveRequest leave = mapper.toEntity(dto, employee);

        leave = leaveRepository.save(leave);

        return mapper.toDto(leave);
    }

    private Set<LocalDate> getHolidayDates(Long officeId) {
        return holidayService.getHolidayByOfficeId(officeId)
                .stream()
                .map(HolidayResponseDTO::getDate)
                .collect(Collectors.toSet());
    }

    private void validateLeaveDates(LocalDate start, LocalDate end, Set<LocalDate> holidays) {

        if (end.isBefore(start)) {
            throw new BadRequestException("End date cannot be before start date");
        }

        boolean hasWorkingDay = false;

        for (LocalDate date = start; !date.isAfter(end); date = date.plusDays(1)) {
            if (!isNonWorkingDay(date, holidays)) {
                hasWorkingDay = true;
                break;
            }
        }

        // ❌ Case 1: Only weekend/holiday
        if (!hasWorkingDay) {
            throw new BadRequestException("Cannot apply leave only on weekends/holidays");
        }

        long totalDays = start.datesUntil(end.plusDays(1)).count();

        // 🔥 IMPORTANT FIX
        if (totalDays == 2) {

            boolean startWorking = !isNonWorkingDay(start, holidays);
            boolean endWorking   = !isNonWorkingDay(end, holidays);

            // ❌ Mixed pair like Fri-Sat OR Sun-Mon
            if (startWorking != endWorking) {
                throw new BadRequestException(
                        "Invalid leave combination. Cannot mix working and non-working day in short leave"
                );
            }
        }
    }

    private boolean isWeekend(LocalDate date) {
        return date.getDayOfWeek() == DayOfWeek.SATURDAY ||
                date.getDayOfWeek() == DayOfWeek.SUNDAY;
    }

    private boolean isHoliday(LocalDate date, Set<LocalDate> holidays) {
        return holidays.contains(date);
    }

    private boolean isNonWorkingDay(LocalDate date, Set<LocalDate> holidays) {
        return isWeekend(date) || isHoliday(date, holidays);
    }
}

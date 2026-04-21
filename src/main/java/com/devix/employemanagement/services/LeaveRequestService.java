package com.devix.employemanagement.services;

import com.devix.employemanagement.Mappers.LeaveRequestMapper;
import com.devix.employemanagement.dtos.leaveDtos.LeaveRequestCreateDto;
import com.devix.employemanagement.dtos.leaveDtos.LeaveRequestResponseDto;
import com.devix.employemanagement.entities.LeaveRequest;
import com.devix.employemanagement.entities.User.Employee;
import com.devix.employemanagement.repo.LeaveRequestRepository;
import com.devix.employemanagement.repo.userRepo.EmployeeRepository;
import com.devix.employemanagement.utils.enums.LeaveStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LeaveRequestService {

    private final LeaveRequestRepository leaveRepository;
    private final EmployeeRepository employeeRepository;
    private final LeaveRequestMapper mapper;

    /* ================= APPLY LEAVE ================= */
    public LeaveRequestResponseDto applyLeave(LeaveRequestCreateDto dto) {

        Employee employee = employeeRepository.findById(dto.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        // validation
        if (dto.getEndDate().isBefore(dto.getStartDate())) {
            throw new RuntimeException("End date cannot be before start date");
        }

        LeaveRequest leave = mapper.toEntity(dto, employee);

        leave = leaveRepository.save(leave);

        return mapper.toDto(leave);
    }

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
}

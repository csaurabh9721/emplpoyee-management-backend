package com.devix.employemanagement.Mappers;

import com.devix.employemanagement.dtos.leaveDtos.LeaveRequestCreateDto;
import com.devix.employemanagement.dtos.leaveDtos.LeaveRequestResponseDto;
import com.devix.employemanagement.entities.LeaveRequest;
import com.devix.employemanagement.entities.User.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class LeaveRequestMapper {

    public LeaveRequest toEntity(LeaveRequestCreateDto dto, Employee employee) {
        return LeaveRequest.builder()
                .employee(employee)
                .leaveType(dto.getLeaveType())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .totalDays(calculateDays(dto.getStartDate(), dto.getEndDate()))
                .reason(dto.getReason())
                .approvedBy(employee.getManagerId())
                .build();
    }

    public LeaveRequestResponseDto toDto(LeaveRequest entity) {
        return LeaveRequestResponseDto.builder()
                .id(entity.getId())
                .employeeId(entity.getEmployee().getId())
                .employeeName(entity.getEmployee().getFullName()) // adjust field
                .leaveType(entity.getLeaveType())
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .totalDays(entity.getTotalDays())
                .status(entity.getStatus())
                .reason(entity.getReason())
                .appliedAt(entity.getAppliedAt())
                .actionedAt(entity.getActionedAt())
                .build();
    }

    private int calculateDays(LocalDate start, LocalDate end) {
        return (int) (end.toEpochDay() - start.toEpochDay()) + 1;
    }
}

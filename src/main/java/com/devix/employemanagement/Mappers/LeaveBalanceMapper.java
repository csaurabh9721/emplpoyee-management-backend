package com.devix.employemanagement.Mappers;

import com.devix.employemanagement.dtos.leaveBalanceDto.LeaveBalanceRequestDto;
import com.devix.employemanagement.dtos.leaveBalanceDto.LeaveBalanceResponseDto;
import com.devix.employemanagement.entities.Employee;
import com.devix.employemanagement.entities.LeaveBalance;
import org.springframework.stereotype.Component;

@Component
public class LeaveBalanceMapper {

    public LeaveBalance toEntity(LeaveBalanceRequestDto dto, Employee employee) {
        if (dto == null) return null;

        return LeaveBalance.builder()
                .employee(employee)
                .leaveType(dto.getLeaveType())
                .totalAllowed(dto.getTotalAllowed())
                .used(dto.getUsed())
                .year(dto.getYear())
                .build();
    }

    public LeaveBalanceResponseDto toDto(LeaveBalance entity) {
        if (entity == null) return null;

        return LeaveBalanceResponseDto.builder()
                .id(entity.getId())
                .employeeId(entity.getEmployee().getId())
                .leaveType(entity.getLeaveType())
                .totalAllowed(entity.getTotalAllowed())
                .used(entity.getUsed())
                .remaining(entity.getTotalAllowed() - entity.getUsed())
                .year(entity.getYear())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}


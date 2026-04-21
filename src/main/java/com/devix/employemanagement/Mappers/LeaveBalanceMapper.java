package com.devix.employemanagement.Mappers;

import com.devix.employemanagement.dtos.leaveDtos.LeaveBalanceRequestDto;
import com.devix.employemanagement.dtos.leaveDtos.LeaveBalanceResponseDto;
import com.devix.employemanagement.entities.User.Employee;
import com.devix.employemanagement.entities.LeaveBalance;
import org.springframework.stereotype.Component;

@Component
public class LeaveBalanceMapper {


    public LeaveBalanceResponseDto toDto(LeaveBalance entity) {
        if (entity == null) return null;

        return LeaveBalanceResponseDto.builder()
                .id(entity.getId())
                .leaveType(entity.getLeaveType())
                .totalAllowed(entity.getTotalAllowed())
                .used(entity.getUsed())
                .remaining(entity.getTotalAllowed() - entity.getUsed())
                .year(entity.getYear())
                .leaveTypeFullName(_getFullName(entity.getLeaveType().name()))
                .build();
    }

    private String _getFullName(String value) {
        return switch (value.toUpperCase()) {
            case "EL" -> "Earned Leave";
            case "CL" -> "Casual Leave";
            case "RH" -> "Restricted Holiday";
            case "SL" -> "Sick Leave";
            default -> value;
        };
    }


}


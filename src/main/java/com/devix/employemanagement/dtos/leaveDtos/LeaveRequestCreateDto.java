package com.devix.employemanagement.dtos.leaveDtos;

import com.devix.employemanagement.utils.enums.LeaveType;
import lombok.Data;

import java.time.LocalDate;

@Data
public class LeaveRequestCreateDto {

    private Long employeeId;
    private LeaveType leaveType;
    private LocalDate startDate;
    private LocalDate endDate;
    private String reason;
}

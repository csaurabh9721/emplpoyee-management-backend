package com.devix.employemanagement.dtos.leaveDtos;

import com.devix.employemanagement.utils.enums.LeaveStatus;
import com.devix.employemanagement.utils.enums.LeaveType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class LeaveRequestResponseDto {

    private Long id;
    private Long employeeId;
    private String employeeName;

    private LeaveType leaveType;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer totalDays;

    private LeaveStatus status;
    private String reason;

    private LocalDateTime appliedAt;
    private LocalDateTime actionedAt;
}

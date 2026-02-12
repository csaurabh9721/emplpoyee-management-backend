package com.devix.employemanagement.dtos.leaveBalanceDto;

import com.devix.employemanagement.entities.LeaveType;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LeaveBalanceResponseDto {

    private Long id;

    private Long employeeId;

    private LeaveType leaveType;

    private Integer totalAllowed;

    private Integer used;

    private Integer remaining;

    private Integer year;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}


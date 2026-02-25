package com.devix.employemanagement.dtos.leaveBalanceDto;

import com.devix.employemanagement.LeaveType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LeaveBalanceRequestDto {

    private Long employeeId;

    private LeaveType leaveType;

    private Integer totalAllowed;

    private Integer used;

    private Integer year;
}

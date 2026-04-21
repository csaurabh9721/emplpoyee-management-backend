package com.devix.employemanagement.dtos.leaveDtos;

import com.devix.employemanagement.utils.enums.LeaveType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LeaveBalanceResponseDto {

    private Long id;

    private LeaveType leaveType;


    private Integer totalAllowed;

    private Integer used;

    private Integer remaining;

    private Integer year;
    private String leaveTypeFullName;


}


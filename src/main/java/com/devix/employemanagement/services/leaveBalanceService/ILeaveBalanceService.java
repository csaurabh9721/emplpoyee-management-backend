package com.devix.employemanagement.services.leaveBalanceService;

import com.devix.employemanagement.dtos.leaveDtos.LeaveBalanceResponseDto;
import com.devix.employemanagement.utils.enums.LeaveType;

import java.util.List;

public interface ILeaveBalanceService {

    List<LeaveBalanceResponseDto> getLeaveBalanceByEmployeeId(Long employeeId);

    List<LeaveType> getLeaveTypes();
}

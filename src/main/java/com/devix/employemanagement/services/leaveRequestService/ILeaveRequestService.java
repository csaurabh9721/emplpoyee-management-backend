package com.devix.employemanagement.services.leaveRequestService;

import com.devix.employemanagement.dtos.leaveDtos.LeaveRequestCreateDto;
import com.devix.employemanagement.dtos.leaveDtos.LeaveRequestResponseDto;
import com.devix.employemanagement.utils.enums.LeaveStatus;

import java.util.List;

public interface ILeaveRequestService {

    List<LeaveRequestResponseDto> getAllLeaves();

    List<LeaveRequestResponseDto> getAllLeavesByEmployeeId(Long employeeId);

    List<LeaveRequestResponseDto> getAllLeavesByApprovedBy(Long approvalId);

    LeaveRequestResponseDto withdrawnLeave(Long id);

    LeaveRequestResponseDto updateStatus(Long id, LeaveStatus status);

    LeaveRequestResponseDto applyLeave(LeaveRequestCreateDto dto);
}

package com.devix.employemanagement.controllers;

import com.devix.employemanagement.dtos.ApiResponse;
import com.devix.employemanagement.dtos.leaveBalanceDto.LeaveBalanceResponseDto;
import com.devix.employemanagement.services.leaveBalanceService.LeaveBalanceService;
import com.devix.employemanagement.utils.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/leaveBalance")
@RequiredArgsConstructor
public class LeaveBalanceController {
    private final LeaveBalanceService leaveBalanceService;

    @GetMapping("/getLeave")
    public ResponseEntity<ApiResponse<List<LeaveBalanceResponseDto>>> getLeaveBalanceByEmployee() {
        Long employeeId = SecurityUtil.getCurrentEmployeeId();
        List<LeaveBalanceResponseDto> leaves = leaveBalanceService.getLeaveBalanceByEmployeeId(employeeId);
        return ResponseEntity.ok(new ApiResponse<>(200, "Leave Balance fetched successfully", leaves));
    }
}

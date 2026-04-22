package com.devix.employemanagement.controllers;

import com.devix.employemanagement.dtos.ApiResponse;
import com.devix.employemanagement.dtos.leaveDtos.LeaveRequestCreateDto;
import com.devix.employemanagement.dtos.leaveDtos.LeaveRequestResponseDto;
import com.devix.employemanagement.services.LeaveRequestService;
import com.devix.employemanagement.utils.enums.LeaveStatus;
import com.devix.employemanagement.utils.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Role;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/leaves")
@RequiredArgsConstructor
public class LeaveRequestController {

    private final LeaveRequestService service;

    /* ================= APPLY ================= */
    @PostMapping("/apply")
    public ResponseEntity<ApiResponse<LeaveRequestResponseDto>> applyLeave(
            @RequestBody LeaveRequestCreateDto dto) {

        return ResponseEntity.ok(new ApiResponse<>(201, "Applied Leave.", service.applyLeave(dto)));
    }

    /* ================= GET ALL ================= */

    @GetMapping()
    public ResponseEntity<ApiResponse<List<LeaveRequestResponseDto>>> getAll() {
        return ResponseEntity.ok(new ApiResponse<>(201, "Leaved Fetched Successfully.", service.getAllLeaves()));
    }

    @GetMapping("/getLeaveForEmployee")
    public ResponseEntity<ApiResponse<List<LeaveRequestResponseDto>>> getAllForEmployee() {
        return ResponseEntity.ok(new ApiResponse<>(201, "Leaved Fetched Successfully.", service.getAllLeavesByEmployeeId(SecurityUtil.getCurrentEmployeeId())));
    }

    @GetMapping("/getLeaveForApproval")
    public ResponseEntity<ApiResponse<List<LeaveRequestResponseDto>>> getAllForApproval() {
        return ResponseEntity.ok(new ApiResponse<>(201, "Leaved Fetched Successfully.", service.getAllLeavesByApprovedBy(SecurityUtil.getCurrentEmployeeId())));
    }


    /* ================= APPROVE ================= */
    @PutMapping("/approve/{id}")
    public ResponseEntity<ApiResponse<LeaveRequestResponseDto>> approve(@PathVariable Long id) {
        return ResponseEntity.ok(new ApiResponse<>(201, "Leaved Approve Successfully.", service.updateStatus(id, LeaveStatus.APPROVED)));
    }

    /* ================= REJECT ================= */
    @PutMapping("/reject/{id}")
    public ResponseEntity<ApiResponse<LeaveRequestResponseDto>> reject(@PathVariable Long id) {
        return ResponseEntity.ok(new ApiResponse<>(201, "Leaved Reject Successfully.", service.updateStatus(id, LeaveStatus.REJECTED)));
    }


    /* ================= Withdrawn ================= */
    @PutMapping("/Withdrawn/{id}")
    public ResponseEntity<ApiResponse<LeaveRequestResponseDto>> Withdrawn(@PathVariable Long id) {
        return ResponseEntity.ok(new ApiResponse<>(201, "Leaved Withdrawn Successfully.", service.withdrawnLeave(id)));
    }
}

package com.devix.employemanagement.controllers;

import com.devix.employemanagement.dtos.ApiResponse;
import com.devix.employemanagement.dtos.dashboardDto.DashboardResponseDto;
import com.devix.employemanagement.services.dashboardService.DashboardService;
import com.devix.employemanagement.utils.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/dashboardData")
    public ResponseEntity<ApiResponse<DashboardResponseDto>> getDashboard() {

        return ResponseEntity.ok(new ApiResponse<>(200, "Data fetched successfully.", dashboardService.getDashboard(SecurityUtil.getCurrentEmployeeId())));
    }

    @GetMapping("/dashboardDataRedis")
    public ResponseEntity<ApiResponse<DashboardResponseDto>> getDashboardRedis() {

        return ResponseEntity.ok(new ApiResponse<>(200, "Data fetched successfully.", dashboardService.getDashboardRedis(SecurityUtil.getCurrentEmployeeId())));
    }

    @GetMapping("/dashboardDataRedisDelete")
    public ResponseEntity<ApiResponse<DashboardResponseDto>> getDashboardRedisDelete() {

        return ResponseEntity.ok(new ApiResponse<>(200, "Data fetched successfully.", dashboardService.getDashboardRedisDeleted(SecurityUtil.getCurrentEmployeeId())));
    }
}

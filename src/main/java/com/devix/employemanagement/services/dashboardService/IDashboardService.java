package com.devix.employemanagement.services.dashboardService;

import com.devix.employemanagement.dtos.dashboardDto.DashboardResponseDto;

public interface IDashboardService {

    DashboardResponseDto getDashboard(Long employeeId);
    DashboardResponseDto getDashboardRedis(Long employeeId);
    DashboardResponseDto getDashboardRedisDeleted(Long employeeId);
}

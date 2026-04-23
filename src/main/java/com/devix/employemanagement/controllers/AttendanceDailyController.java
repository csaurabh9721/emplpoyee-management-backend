package com.devix.employemanagement.controllers;

import com.devix.employemanagement.dtos.ApiResponse;
import com.devix.employemanagement.dtos.AttendanceDailyDto.AttendanceDailyRequestDto;
import com.devix.employemanagement.dtos.AttendanceDailyDto.AttendanceDailyResponseDto;
import com.devix.employemanagement.dtos.AttendanceDailyDto.TeamAttendanceResponseDto;
import com.devix.employemanagement.services.AttendanceService.AttendanceDailyService;
import com.devix.employemanagement.utils.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/attendance")
@RequiredArgsConstructor
public class AttendanceDailyController {

    private final AttendanceDailyService attendanceService;

    @PostMapping("/punchInOut")
    public ResponseEntity<ApiResponse<AttendanceDailyResponseDto>> markAttendance(
            @RequestBody AttendanceDailyRequestDto request
    ) {

        AttendanceDailyResponseDto responseDto = attendanceService.markAttendance(request);
        ApiResponse<AttendanceDailyResponseDto> response = new ApiResponse<>(201, "Attendance punched.", responseDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/getAttendanceByDateRange")
    public ResponseEntity<ApiResponse<List<AttendanceDailyResponseDto>>> getAttendanceByEmployee(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate
    ) {
        List<AttendanceDailyResponseDto> responseDto = attendanceService.getAttendanceByEmployeeAndDateRange(SecurityUtil.getCurrentEmployeeId(), startDate, endDate);
        ApiResponse<List<AttendanceDailyResponseDto>> response = new ApiResponse<>(200, "Fetched Success.", responseDto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/getAttendanceForMangerByDateRange")
    public ResponseEntity<ApiResponse<List<TeamAttendanceResponseDto>>> getAttendanceForMangerByDateRange(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate
    ) {
        /// todo remove hardcode id 2L
        List<TeamAttendanceResponseDto> responseDto = attendanceService.getTeamAttendance(2L, startDate, endDate);
        ApiResponse<List<TeamAttendanceResponseDto>> response = new ApiResponse<>(200, "Fetched Success.", responseDto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}

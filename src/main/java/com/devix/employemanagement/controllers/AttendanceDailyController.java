package com.devix.employemanagement.controllers;
import com.devix.employemanagement.dtos.ApiResponse;
import com.devix.employemanagement.dtos.AttendanceDailyDto.AttendanceDailyRequestDto;
import com.devix.employemanagement.dtos.AttendanceDailyDto.AttendanceDailyResponseDto;
import com.devix.employemanagement.services.AttendanceService.AttendanceDailyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/getAttendanceByEmployeeId/{employeeId}")
    public ResponseEntity<ApiResponse<List<AttendanceDailyResponseDto>>> getAttendanceByEmployee(
            @PathVariable Long employeeId
    ) {
        List<AttendanceDailyResponseDto> responseDto = attendanceService.getAttendanceByEmployee(employeeId);
        ApiResponse<List<AttendanceDailyResponseDto>> response = new ApiResponse<>(200, "Fetched Success.", responseDto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}

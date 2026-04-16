package com.devix.employemanagement.controllers;

import com.devix.employemanagement.dtos.ApiResponse;
import com.devix.employemanagement.dtos.EmployeeDto.requestDto.EmployeeCreateDto;
import com.devix.employemanagement.dtos.EmployeeDto.responseDto.EmployeeResponseDto;
import com.devix.employemanagement.dtos.EmployeeDto.requestDto.EmployeeUpdateProfileDto;
import com.devix.employemanagement.services.employeeService.EmployeeService;
import com.devix.employemanagement.utils.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<EmployeeResponseDto>> create(
            @RequestBody EmployeeCreateDto dto) {

        EmployeeResponseDto response = employeeService.create(dto);

        return ResponseEntity.ok(
                new ApiResponse<>(200, "Employee created successfully", response)
        );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse<List<EmployeeResponseDto>>> getAll() {
        return ResponseEntity.ok(
                new ApiResponse<>(200, "Employees fetched successfully",
                        employeeService.getAll())
        );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        employeeService.delete(id);
        return ResponseEntity.ok(
                new ApiResponse<>(200, "Employee deleted successfully", null)
        );
    }

    @GetMapping("/getEmployeeProfile")
    public ResponseEntity<ApiResponse<EmployeeResponseDto>> getById() {
        return ResponseEntity.ok(
                new ApiResponse<>(200, "Employee fetched successfully",
                        employeeService.getById(SecurityUtil.getCurrentEmployeeId()))
        );
    }

    @PutMapping("/update-profile")
    public ResponseEntity<ApiResponse<EmployeeResponseDto>> updateProfile(
            @RequestBody EmployeeUpdateProfileDto dto) {

        EmployeeResponseDto response = employeeService.updateProfile(dto);
        return ResponseEntity.ok(
                new ApiResponse<>(200, "Employee updated successfully", response));
    }
}

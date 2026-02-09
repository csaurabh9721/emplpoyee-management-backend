package com.devix.employemanagement.controllers;

import com.devix.employemanagement.dtos.ApiResponse;
import com.devix.employemanagement.dtos.EmployeeDto.EmployeeRequestDto;
import com.devix.employemanagement.dtos.EmployeeDto.EmployeeResponseDto;
import com.devix.employemanagement.services.employeeService.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<EmployeeResponseDto>> create(
            @RequestBody EmployeeRequestDto dto) {

        EmployeeResponseDto response = employeeService.create(dto);

        return ResponseEntity.ok(
                new ApiResponse<>(200, "Employee created successfully", response)
        );
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<ApiResponse<EmployeeResponseDto>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(
                new ApiResponse<>(200, "Employee fetched successfully",
                        employeeService.getById(id))
        );
    }

    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse<List<EmployeeResponseDto>>> getAll() {
        return ResponseEntity.ok(
                new ApiResponse<>(200, "Employees fetched successfully",
                        employeeService.getAll())
        );
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        employeeService.delete(id);
        return ResponseEntity.ok(
                new ApiResponse<>(200, "Employee deleted successfully", null)
        );
    }
}

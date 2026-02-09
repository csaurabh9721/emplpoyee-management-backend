package com.devix.employemanagement.controllers;


import com.devix.employemanagement.dtos.ApiResponse;
import com.devix.employemanagement.dtos.authDto.LoginRequest;
import com.devix.employemanagement.dtos.authDto.LoginResponse;
import com.devix.employemanagement.entities.Employee;
import com.devix.employemanagement.repo.EmployeeRepository;
import com.devix.employemanagement.services.authService.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    protected ResponseEntity<ApiResponse<LoginResponse>> login(@RequestBody LoginRequest loginRequest) {
        LoginResponse response = authService.login(loginRequest);
        return ResponseEntity.ok(new ApiResponse<>(200, "Login Successfully", response
        ));
    }

}

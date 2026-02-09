package com.devix.employemanagement.services.authService;

import com.devix.employemanagement.dtos.authDto.LoginRequest;
import com.devix.employemanagement.dtos.authDto.LoginResponse;
import com.devix.employemanagement.entities.Employee;
import com.devix.employemanagement.entities.User;
import com.devix.employemanagement.exceptions.BadRequestException;
import com.devix.employemanagement.exceptions.ResourceNotFoundException;
import com.devix.employemanagement.repo.EmployeeRepository;
import com.devix.employemanagement.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {
    private final UserRepository userRepository;
    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;

    public LoginResponse login(LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmailId()).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPasswordHash())) {
            throw new BadRequestException("Wrong password");
        }
        Employee employee = employeeRepository.findByUserId(user.getId().toString());
        return LoginResponse.builder().userId(user.getId()).employeeCode(employee.getEmployeeCode()).employeeName(employee.getFullName()).accessToken("").refreshToken("").build();
    }
}

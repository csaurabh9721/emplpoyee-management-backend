package com.devix.employemanagement.services.authService;

import com.devix.employemanagement.dtos.authDto.LoginRequest;
import com.devix.employemanagement.dtos.authDto.LoginResponse;
import com.devix.employemanagement.dtos.authDto.RefreshTokenResponse;
import com.devix.employemanagement.entities.User.Employee;
import com.devix.employemanagement.entities.User.User;
import com.devix.employemanagement.exceptions.BadRequestException;
import com.devix.employemanagement.exceptions.ResourceNotFoundException;
import com.devix.employemanagement.repo.userRepo.EmployeeRepository;
import com.devix.employemanagement.repo.userRepo.UserRepository;
import com.devix.employemanagement.utils.security.JwtUtil;
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
    private final JwtUtil jwtUtil;

    public LoginResponse login(LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmailId()).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPasswordHash())) {
            throw new BadRequestException("Wrong password");
        }
        Employee employee = employeeRepository.findByUserId(user.getId().toString());
        String accessToken = jwtUtil.generateToken(loginRequest.getEmailId(), employee.getId(), user.getRole().toString());
        String refreshToken = jwtUtil.generateRefreshToken(loginRequest.getEmailId());
        return LoginResponse.builder().userId(user.getId()).employeeCode(employee.getEmployeeCode()).employeeName(employee.getFullName()).accessToken(accessToken).refreshToken(refreshToken).build();
    }

    public RefreshTokenResponse refreshToken(String refreshToken) {

        if (!jwtUtil.isTokenValid(refreshToken)) {
            throw new BadRequestException("Invalid refresh token");
        }
        String email = jwtUtil.extractEmail(refreshToken);
        User user = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Employee employee = employeeRepository.findByUserId(user.getId().toString());
        String newAccessToken = jwtUtil.generateToken(email, employee.getId(), user.getRole().toString());
        String newRefreshToken = jwtUtil.generateRefreshToken(email);
        return RefreshTokenResponse.builder()
                .accessToken(newAccessToken)
                .refreshToken(newRefreshToken)
                .build();
    }
}

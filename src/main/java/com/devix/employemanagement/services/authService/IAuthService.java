package com.devix.employemanagement.services.authService;

import com.devix.employemanagement.dtos.authDto.LoginRequest;
import com.devix.employemanagement.dtos.authDto.LoginResponse;
import com.devix.employemanagement.dtos.authDto.RefreshTokenResponse;

public interface IAuthService {

    LoginResponse login(LoginRequest loginRequest);

    RefreshTokenResponse refreshToken(String refreshToken);
}

package com.devix.employemanagement.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private String userId;
    private String employeeName;
    private String employeeCode;
    private String accessToken;
    private String refreshToken;
}

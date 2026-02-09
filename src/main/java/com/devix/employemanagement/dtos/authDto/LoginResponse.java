package com.devix.employemanagement.dtos.authDto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginResponse {
    private Long userId;
    private String employeeName;
    private String employeeCode;
    private String accessToken;
    private String refreshToken;
}

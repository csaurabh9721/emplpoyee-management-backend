package com.devix.employemanagement.dtos.authDto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RefreshTokenResponse {
    private String accessToken;
    private String refreshToken;
}

package com.devix.employemanagement.dtos.authDto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RefreshTokenRequest {
    private String refreshToken;
}

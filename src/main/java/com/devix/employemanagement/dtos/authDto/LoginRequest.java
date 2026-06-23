package com.devix.employemanagement.dtos.authDto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoginRequest {
    private String emailId;
    private String password;
}

package com.devix.employemanagement.dtos;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {

    private Long id;
    private String email;
    private String mobile;
    private String role;
    private Boolean active;
}

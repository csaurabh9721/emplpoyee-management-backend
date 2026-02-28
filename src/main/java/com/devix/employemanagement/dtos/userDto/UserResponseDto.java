package com.devix.employemanagement.dtos.userDto;

import com.devix.employemanagement.utils.RoleEnum;
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
    private RoleEnum role;
    private Boolean active;
}

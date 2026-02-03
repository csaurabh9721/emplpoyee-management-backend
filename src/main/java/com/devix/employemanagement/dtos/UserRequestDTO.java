package com.devix.employemanagement.dtos;

import com.devix.employemanagement.RoleEnum;
import lombok.Data;


// UserRequestDTO.java
@Data
public class UserRequestDTO {
    private Long organizationId;
    private String email;
    private String mobile;
    private String passwordHash;
    private RoleEnum role;
    private Boolean active;
}
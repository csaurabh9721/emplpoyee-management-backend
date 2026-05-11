package com.devix.employemanagement.dtos.userDto;

import com.devix.employemanagement.utils.enums.RoleEnum;
import lombok.Data;

@Data
public class UpdateUserRequest {
        private Long organizationId;
        private String email;
        private String mobile;
        private Boolean active;
}

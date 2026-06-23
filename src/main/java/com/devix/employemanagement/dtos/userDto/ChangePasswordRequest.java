package com.devix.employemanagement.dtos.userDto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangePasswordRequest {
    @NonNull
    private Long id;
    @Size(min = 4)
    private String oldPassword;
    @Size(min = 4)
    private String newPassword;
    @Size(min = 4)
    private String confirmPassword;
}

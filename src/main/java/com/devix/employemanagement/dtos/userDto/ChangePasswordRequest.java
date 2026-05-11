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
    @Size(min = 6)
    private String oldPassword;
    @Size(min = 6)
    private String newPassword;
    @Size(min = 6)
    private String confirmPassword;
}

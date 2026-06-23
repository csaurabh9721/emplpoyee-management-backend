package com.devix.employemanagement.controllers;

import com.devix.employemanagement.dtos.ApiResponse;
import com.devix.employemanagement.dtos.userDto.ChangePasswordRequest;
import com.devix.employemanagement.dtos.userDto.UpdateUserRequest;
import com.devix.employemanagement.dtos.userDto.UserRequestDTO;
import com.devix.employemanagement.dtos.userDto.UserResponseDto;
import com.devix.employemanagement.services.UserService.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<UserResponseDto>> createUser(@RequestBody UserRequestDTO dto) {
        ApiResponse<UserResponseDto> data = new ApiResponse<>(HttpStatus.CREATED.value(), "User Created Successfully.", userService.createUser(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(data);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getById/{id}")
    public ResponseEntity<ApiResponse<UserResponseDto>> getUser(@PathVariable Long id) {

        ApiResponse<UserResponseDto> data =
                new ApiResponse<>(
                        HttpStatus.OK.value(),
                        "Fetching User",
                        userService.getUser(id)
                );

        return ResponseEntity.ok(data);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<UserResponseDto>>> getAllUsers() {

        ApiResponse<List<UserResponseDto>> data =
                new ApiResponse<>(
                        HttpStatus.OK.value(),
                        "Fetching all users",
                        userService.getAllUsers()
                );

        return ResponseEntity.ok(data);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/updateById/{id}")
    public ResponseEntity<ApiResponse<UserResponseDto>> updateUser(
            @PathVariable Long id,
            @RequestBody UpdateUserRequest dto
    ) {

        ApiResponse<UserResponseDto> data =
                new ApiResponse<>(
                        HttpStatus.OK.value(),
                        "User successfully updated",
                        userService.updateUser(id, dto)
                );

        return ResponseEntity.ok(data);
    }

    @PutMapping("/changePassword")
    public ResponseEntity<ApiResponse<Boolean>> changePassword(
            @Valid @RequestBody ChangePasswordRequest dto
    ) {

        boolean isPasswordChanged = userService.changePassword(dto);

        ApiResponse<Boolean> data =
                new ApiResponse<>(
                        HttpStatus.OK.value(),
                        "Password changed successfully",
                        isPasswordChanged
                );

        return ResponseEntity.ok(data);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<ApiResponse<String>> deleteUser(@PathVariable Long id) {

        userService.deleteUser(id);

        ApiResponse<String> data =
                new ApiResponse<>(
                        HttpStatus.OK.value(),
                        "User successfully deleted",
                        "User deleted successfully"
                );

        return ResponseEntity.status(HttpStatus.OK).body(data);
    }
}

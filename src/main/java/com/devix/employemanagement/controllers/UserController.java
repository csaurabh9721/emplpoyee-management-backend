package com.devix.employemanagement.controllers;

import com.devix.employemanagement.dtos.UserRequestDTO;
import com.devix.employemanagement.dtos.UserResponseDto;
import com.devix.employemanagement.services.UserService.UserService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserResponseDto createUser(@RequestBody UserRequestDTO dto) {
        return userService.createUser(dto);
    }

    @GetMapping("/{id}")
    public UserResponseDto getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @GetMapping
    public List<UserResponseDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @PutMapping("/{id}")
    public UserResponseDto updateUser(@PathVariable Long id, @RequestBody UserRequestDTO dto) {
        return userService.updateUser(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}

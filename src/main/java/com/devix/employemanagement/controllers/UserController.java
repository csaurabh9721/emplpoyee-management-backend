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

    @PostMapping("/create")
    public UserResponseDto createUser(@RequestBody UserRequestDTO dto) {
        return userService.createUser(dto);
    }

    @GetMapping("/getById/{id}")
    public UserResponseDto getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @GetMapping("/all")
    public List<UserResponseDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @PutMapping("/updateById/{id}")
    public UserResponseDto updateUser(@PathVariable Long id, @RequestBody UserRequestDTO dto) {
        return userService.updateUser(id, dto);
    }

    @DeleteMapping("/deleteById/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}

package com.devix.employemanagement.services.UserService;

import com.devix.employemanagement.dtos.userDto.UserRequestDTO;
import com.devix.employemanagement.dtos.userDto.UserResponseDto;

import java.util.List;

public interface IUserService {

    UserResponseDto createUser(UserRequestDTO dto);

    UserResponseDto getUser(Long id);

    List<UserResponseDto> getAllUsers();

    UserResponseDto updateUser(Long id, UserRequestDTO dto);

    void deleteUser(Long id);
}

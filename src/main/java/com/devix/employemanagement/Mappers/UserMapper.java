package com.devix.employemanagement.Mappers;

import com.devix.employemanagement.dtos.UserRequestDTO;
import com.devix.employemanagement.dtos.UserResponseDto;
import com.devix.employemanagement.entities.Organization;
import com.devix.employemanagement.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEntity(UserRequestDTO dto, Organization organization) {
        User user = new User();
        user.setOrganization(organization);
        user.setEmail(dto.getEmail());
        user.setMobile(dto.getMobile());
        user.setPasswordHash(dto.getPasswordHash());
        user.setRole(dto.getRole());
        user.setActive(dto.getActive());
        return user;
    }

    public UserResponseDto toResponseDTO(User user) {
        UserResponseDto dto = new UserResponseDto();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setMobile(user.getMobile());
        dto.setRole(user.getRole());
        dto.setActive(user.getActive());
        return dto;
    }
}

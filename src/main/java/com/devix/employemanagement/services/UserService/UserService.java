package com.devix.employemanagement.services.UserService;

import com.devix.employemanagement.Mappers.UserMapper;
import com.devix.employemanagement.dtos.userDto.UserRequestDTO;
import com.devix.employemanagement.dtos.userDto.UserResponseDto;
import com.devix.employemanagement.entities.Organization;
import com.devix.employemanagement.entities.User;
import com.devix.employemanagement.repo.OrganizationRepo;
import com.devix.employemanagement.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final OrganizationRepo organizationRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;


    public UserResponseDto createUser(UserRequestDTO dto) {
        Organization org = organizationRepository.findById(dto.getOrganizationId())
                .orElseThrow(() -> new RuntimeException("Organization not found"));
        User user = userMapper.toEntity(dto, org);
        user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));
        return userMapper.toResponseDTO(userRepository.save(user));
    }

    public UserResponseDto getUser(Long id) {
        return userMapper.toResponseDTO(
                userRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("User not found"))
        );
    }

    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public UserResponseDto updateUser(Long id, UserRequestDTO dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setEmail(dto.getEmail());
        user.setMobile(dto.getMobile());
        user.setPasswordHash(passwordEncoder.encode(dto.getPasswordHash()));
        user.setRole(dto.getRole());
        user.setActive(dto.getActive());
        return userMapper.toResponseDTO(userRepository.save(user));
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
package com.devix.employemanagement.services.UserService;

import com.devix.employemanagement.Mappers.UserMapper;
import com.devix.employemanagement.dtos.UserRequestDTO;
import com.devix.employemanagement.dtos.UserResponseDto;
import com.devix.employemanagement.entities.Organization;
import com.devix.employemanagement.entities.User;
import com.devix.employemanagement.repo.OrganizationRepo;
import com.devix.employemanagement.repo.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final OrganizationRepo organizationRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository,
                       OrganizationRepo organizationRepository,
                       UserMapper userMapper) {
        this.userRepository = userRepository;
        this.organizationRepository = organizationRepository;
        this.userMapper = userMapper;
    }

    public UserResponseDto createUser(UserRequestDTO dto) {
        Organization org = organizationRepository.findById(dto.getOrganizationId())
                .orElseThrow(() -> new RuntimeException("Organization not found"));
        User user = userMapper.toEntity(dto, org);
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
        user.setPasswordHash(dto.getPasswordHash());
        user.setRole(dto.getRole());
        user.setActive(dto.getActive());
        return userMapper.toResponseDTO(userRepository.save(user));
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
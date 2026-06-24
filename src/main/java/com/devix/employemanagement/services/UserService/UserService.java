package com.devix.employemanagement.services.UserService;

import com.devix.employemanagement.Mappers.UserMapper;
import com.devix.employemanagement.dtos.userDto.ChangePasswordRequest;
import com.devix.employemanagement.dtos.userDto.UpdateUserRequest;
import com.devix.employemanagement.dtos.userDto.UserRequestDTO;
import com.devix.employemanagement.dtos.userDto.UserResponseDto;
import com.devix.employemanagement.entities.Organization;
import com.devix.employemanagement.entities.User.User;
import com.devix.employemanagement.exceptions.BadRequestException;
import com.devix.employemanagement.repo.OrganizationRepo;
import com.devix.employemanagement.repo.userRepo.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

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

    @Override
    public UserResponseDto updateUser(Long id, UpdateUserRequest dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setEmail(dto.getEmail());
        user.setMobile(dto.getMobile());
        user.setActive(dto.getActive());
        return userMapper.toResponseDTO(userRepository.save(user));
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }


    public Boolean changePassword( ChangePasswordRequest dto) {

        User user = userRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        log.info(dto.getOldPassword());
        log.info(passwordEncoder.encode(dto.getOldPassword()));
        log.info(user.getPasswordHash());
        boolean isMatched = passwordEncoder.matches(
                dto.getOldPassword(),
                user.getPasswordHash()
        );
        if (!isMatched) {
            throw new BadRequestException("Old password is incorrect");
        }
        if (!dto.getNewPassword().equals(dto.getConfirmPassword())) {
            throw new BadRequestException("New password and confirm password do not match");
        }

        user.setPasswordHash(
                passwordEncoder.encode(dto.getNewPassword())
        );
        userRepository.save(user);
        return true;
    }
}
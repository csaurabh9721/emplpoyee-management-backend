package com.devix.employemanagement.services.employeeService;

import com.devix.employemanagement.Mappers.EmployeeMapper;
import com.devix.employemanagement.dtos.EmployeeDto.EmployeeRequestDto;
import com.devix.employemanagement.dtos.EmployeeDto.EmployeeResponseDto;
import com.devix.employemanagement.entities.*;
import com.devix.employemanagement.exceptions.BadRequestException;
import com.devix.employemanagement.exceptions.ResourceNotFoundException;
import com.devix.employemanagement.repo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final UserRepository userRepository;
    private final OrganizationRepo organizationRepository;
    private final OfficeRepo officeRepository;
    private final EmployeeMapper employeeMapper;
    private final DesignationRepo designationRepo;

    public EmployeeResponseDto create(EmployeeRequestDto dto) {

        if (employeeRepository.existsByEmployeeCode(dto.getEmployeeCode())) {
            throw new BadRequestException("Employee code already exists");
        }

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Organization org = organizationRepository.findById(dto.getOrganizationId())
                .orElseThrow(() -> new ResourceNotFoundException("Organization not found"));

        Office office = null;
        if (dto.getPrimaryOfficeId() != null) {
            office = officeRepository.findById(dto.getPrimaryOfficeId())
                    .orElseThrow(() -> new ResourceNotFoundException("Office not found"));
        }
        Designation designation = designationRepo.findById(dto.getDesignationId())
                .orElseThrow(() -> new ResourceNotFoundException("Designation not found"));

        Employee employee = employeeMapper.toEntity(dto, user, org, office, designation);

        return employeeMapper.toDto(employeeRepository.save(employee));
    }

    public EmployeeResponseDto getById(Long id) {
        Employee emp = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        return employeeMapper.toDto(emp);
    }

    public List<EmployeeResponseDto> getAll() {
        return employeeRepository.findAll()
                .stream()
                .map(employeeMapper::toDto)
                .toList();
    }

    public void delete(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Employee not found");
        }
        employeeRepository.deleteById(id);
    }
}


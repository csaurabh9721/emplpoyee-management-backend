package com.devix.employemanagement.Mappers;

import com.devix.employemanagement.dtos.EmployeeDto.EmployeeRequestDto;
import com.devix.employemanagement.dtos.EmployeeDto.EmployeeResponseDto;
import com.devix.employemanagement.entities.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmployeeMapper {

    public Employee toEntity(EmployeeRequestDto dto,
                             User user,
                             Organization organization,
                             Office office, Designation designation) {

        return Employee.builder()
                .user(user)
                .organization(organization)
                .employeeCode(dto.getEmployeeCode())
                .fullName(dto.getFullName())
                .designation(designation)
                .department(dto.getDepartment())
                .profileImageUrl(dto.getProfileImageUrl())
                .primaryOffice(office)
                .joiningDate(dto.getJoiningDate())
                .status(dto.getStatus())
                .build();
    }

    public EmployeeResponseDto toDto(Employee emp) {
        return EmployeeResponseDto.builder()
                .id(emp.getId())
                .employeeCode(emp.getEmployeeCode())
                .fullName(emp.getFullName())
                .designation(emp.getDesignation())
                .department(emp.getDepartment())
                .profileImageUrl(emp.getProfileImageUrl())
                .joiningDate(emp.getJoiningDate())
                .status(emp.getStatus())
                .userId(emp.getUser().getId())
                .organizationId(emp.getOrganization().getId())
                .primaryOfficeId(
                        emp.getPrimaryOffice() != null ? emp.getPrimaryOffice().getId() : null
                )
                .createdAt(emp.getCreatedAt())
                .build();
    }
}


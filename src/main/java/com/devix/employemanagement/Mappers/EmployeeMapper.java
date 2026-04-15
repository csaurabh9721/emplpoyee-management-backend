package com.devix.employemanagement.Mappers;

import com.devix.employemanagement.dtos.EmployeeDto.*;
import com.devix.employemanagement.entities.*;
import com.devix.employemanagement.entities.User.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmployeeMapper {

    // 🔹 Personal Details
    public EmployeePersonalDetails toPersonal(EmployeeRequestDto dto, Employee emp) {
        return EmployeePersonalDetails.builder()
                .employee(emp)
                .dateOfBirth(dto.getDateOfBirth())
                .gender(dto.getGender() != null ? dto.getGender().name() : null)
                .maritalStatus(dto.getMaritalStatus() != null ? dto.getMaritalStatus().name() : null)
                .bloodGroup(dto.getBloodGroup())
                .panNumber(dto.getPanNumber())
                .aadharNumber(dto.getAadharNumber())
                .build();
    }

    // 🔹 Address
    public EmployeeAddress toAddress(EmployeeRequestDto dto, Employee emp) {
        return EmployeeAddress.builder()
                .employee(emp)
                .address(dto.getAddress())
                .city(dto.getCity())
                .state(dto.getState())
                .postalCode(dto.getPostalCode())
                .country(dto.getCountry())
                .build();
    }

    // 🔹 Emergency Contact
    public EmployeeEmergencyContact toEmergency(EmployeeRequestDto dto, Employee emp) {
        return EmployeeEmergencyContact.builder()
                .employee(emp)
                .name(dto.getEmergencyName())
                .phone(dto.getEmergencyPhone())
                .relation(dto.getEmergencyRelation())
                .build();
    }

    // 🔹 Bank Details
    public EmployeeBankDetails toBank(EmployeeRequestDto dto, Employee emp) {
        return EmployeeBankDetails.builder()
                .employee(emp)
                .bankName(dto.getBankName())
                .accountNumber(dto.getAccountNumber())
                .ifscCode(dto.getIfscCode())
                .build();
    }

    // 🔹 Employment Details
    public EmployeeEmploymentDetails toEmployment(EmployeeRequestDto dto, Employee emp) {
        return EmployeeEmploymentDetails.builder()
                .employee(emp)
                .pfNumber(dto.getPfNumber())
                .esiNumber(dto.getEsiNumber())
                .build();
    }

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

    public EmployeeResponseDto toDto(
            Employee emp,
            EmployeeAddress address,
            EmployeeBankDetails bank,
            EmployeeEmergencyContact emergency,
            EmployeeEmploymentDetails employment,
            EmployeePersonalDetails personal
    ) {
        return EmployeeResponseDto.builder()
                .id(emp.getId())
                .employeeCode(emp.getEmployeeCode())
                .fullName(emp.getFullName())
                .designation(emp.getDesignation())
                .department(emp.getDepartment())
                .profileImageUrl(emp.getProfileImageUrl())
                .joiningDate(emp.getJoiningDate())
                .status(emp.getStatus())
                .userId(emp.getUser() != null ? emp.getUser().getId() : null)
                .organizationId(emp.getOrganization() != null ? emp.getOrganization().getId() : null)
                .primaryOfficeId(
                        emp.getPrimaryOffice() != null ? emp.getPrimaryOffice().getId() : null
                ).employeeAddress(toAddressDto(address))
                .employeeBankDetails(toBankDto(bank))
                .emergencyContact(toEmergencyDto(emergency))
                .employeeEmploymentDetails(toEmploymentDto(employment))
                .personalDetails(toPersonalDto(personal))
                .build();

    }


    public EmployeePersonalDetailsDto toPersonalDto(EmployeePersonalDetails dto) {
        return EmployeePersonalDetailsDto.builder()
                .id(dto.getId())
                .dateOfBirth(dto.getDateOfBirth())
                .gender(dto.getGender())
                .maritalStatus(dto.getMaritalStatus())
                .bloodGroup(dto.getBloodGroup())
                .panNumber(dto.getPanNumber())
                .aadharNumber(dto.getAadharNumber())
                .build();
    }

    // 🔹 Address
    public EmployeeAddressDto toAddressDto(EmployeeAddress dto) {
        return EmployeeAddressDto.builder()
                .id(dto.getId())
                .address(dto.getAddress())
                .city(dto.getCity())
                .state(dto.getState())
                .postalCode(dto.getPostalCode())
                .country(dto.getCountry())
                .build();
    }

    // 🔹 Emergency Contact
    public EmployeeEmergencyContactDto toEmergencyDto(EmployeeEmergencyContact dto) {
        return EmployeeEmergencyContactDto.builder()
                .id(dto.getId())
                .name(dto.getName())
                .phone(dto.getPhone())
                .relation(dto.getRelation())
                .build();
    }

    // 🔹 Bank Details
    public EmployeeBankDetailsDto toBankDto(EmployeeBankDetails dto) {
        return EmployeeBankDetailsDto.builder()
                .id(dto.getId())
                .bankName(dto.getBankName())
                .accountNumber(dto.getAccountNumber())
                .ifscCode(dto.getIfscCode())
                .build();
    }

    // 🔹 Employment Details
    public EmployeeEmploymentDetailsDto toEmploymentDto(EmployeeEmploymentDetails dto) {
        return EmployeeEmploymentDetailsDto.builder()
                .id(dto.getId())
                .pfNumber(dto.getPfNumber())
                .esiNumber(dto.getEsiNumber())
                .build();
    }
}


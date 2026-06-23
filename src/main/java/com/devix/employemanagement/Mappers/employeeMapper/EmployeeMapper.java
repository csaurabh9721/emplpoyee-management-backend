package com.devix.employemanagement.Mappers.employeeMapper;

import com.devix.employemanagement.dtos.EmployeeDto.responseDto.*;
import com.devix.employemanagement.entities.*;
import com.devix.employemanagement.entities.User.*;
import com.devix.employemanagement.utils.MaskingUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import com.devix.employemanagement.dtos.EmployeeDto.responseDto.*;
import com.devix.employemanagement.entities.*;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class EmployeeMapper {

    // 🔹 MAIN MAPPER
    public EmployeeResponseDto toDto(
            Employee emp,
            EmployeeAddress address,
            EmployeeBankDetails bank,
            EmployeeEmergencyContact emergency,
            EmployeeEmploymentDetails employment,
            EmployeePersonalDetails personal
    ) {

        if (emp == null) return null;

        return EmployeeResponseDto.builder()
                .id(emp.getId())
                .employeeCode(emp.getEmployeeCode())
                .fullName(emp.getFullName())
                .designation(emp.getDesignation())
                .department(emp.getDepartment())
                .profileImageUrl(emp.getProfileImageUrl())
                .joiningDate(emp.getJoiningDate())
                .status(emp.getStatus())

                // 🔹 Safe nested mapping
                .userId(getSafe(emp.getUser(), User::getId))
                .phone(getSafe(emp.getUser(), User::getMobile))
                .organizationId(getSafe(emp.getOrganization(), Organization::getId))
                .organizationName(getSafe(emp.getOrganization(), Organization::getName))
                .primaryOfficeId(getSafe(emp.getPrimaryOffice(), Office::getId))
                .primaryOfficeName(getSafe(emp.getPrimaryOffice(), Office::getName))

                // 🔹 Related data mapping
                .employeeAddress(mapIfNotNull(address, this::toAddressDto))
                .employeeBankDetails(mapIfNotNull(bank, this::toBankDto))
                .emergencyContact(mapIfNotNull(emergency, this::toEmergencyDto))
                .employeeEmploymentDetails(mapIfNotNull(employment, this::toEmploymentDto))
                .personalDetails(mapIfNotNull(personal, this::toPersonalDto))

                .employmentType(emp.getEmploymentType())
                .managerId(emp.getManagerId())
                .managerName(emp.getManagerName())
                .build();
    }

    public EmployeeResponseDto toDto(
            Employee emp
    ) {

        if (emp == null) return null;

        return EmployeeResponseDto.builder()
                .id(emp.getId())
                .employeeCode(emp.getEmployeeCode())
                .fullName(emp.getFullName())
                .designation(emp.getDesignation())
                .department(emp.getDepartment())
                .profileImageUrl(emp.getProfileImageUrl())
                .joiningDate(emp.getJoiningDate())
                .status(emp.getStatus())

                // 🔹 Safe nested mapping
                .userId(getSafe(emp.getUser(), User::getId))
                .phone(getSafe(emp.getUser(), User::getMobile))
                .organizationId(getSafe(emp.getOrganization(), Organization::getId))
                .organizationName(getSafe(emp.getOrganization(), Organization::getName))
                .primaryOfficeId(getSafe(emp.getPrimaryOffice(), Office::getId))
                .primaryOfficeName(getSafe(emp.getPrimaryOffice(), Office::getName))

                // 🔹 Related data mapping
                .employeeAddress(mapIfNotNull(emp.getAddress(), this::toAddressDto))
                .employeeBankDetails(mapIfNotNull(emp.getBankDetails(), this::toBankDto))
                .emergencyContact(mapIfNotNull(emp.getEmergencyContact(), this::toEmergencyDto))
                .employeeEmploymentDetails(mapIfNotNull(emp.getEmploymentDetails(), this::toEmploymentDto))
                .personalDetails(mapIfNotNull(emp.getPersonalDetails(), this::toPersonalDto))
                .employmentType(emp.getEmploymentType())
                .managerId(emp.getManagerId())
                .managerName(emp.getManagerName())
                .build();
    }

    // =========================================================
    // 🔹 HELPER METHODS (CLEAN + REUSABLE)
    // =========================================================

    private <T, R> R mapIfNotNull(T source, Function<T, R> mapper) {
        return source == null ? null : mapper.apply(source);
    }

    private <T, R> R getSafe(T source, Function<T, R> mapper) {
        return source == null ? null : mapper.apply(source);
    }

    // =========================================================
    // 🔹 PERSONAL DETAILS
    // =========================================================
    public EmployeePersonalDetailsDto toPersonalDto(EmployeePersonalDetails entity) {
        if (entity == null) return null;

        return EmployeePersonalDetailsDto.builder()
                .id(entity.getId())
                .dateOfBirth(entity.getDateOfBirth())
                .gender(entity.getGender())
                .maritalStatus(entity.getMaritalStatus())
                .bloodGroup(entity.getBloodGroup())
                .panNumber(MaskingUtil.maskPan(entity.getPanNumber()))               // 🔒 masked
                .aadharNumber(MaskingUtil.maskAadhar(entity.getAadharNumber()))         // 🔒 masked
                .personalEmail(entity.getPersonalEmail())
                .alternateMobileNumber(entity.getAlternateMobileNumber())
                .build();
    }

    // =========================================================
    // 🔹 ADDRESS
    // =========================================================
    public EmployeeAddressDto toAddressDto(EmployeeAddress entity) {
        if (entity == null) return null;

        return EmployeeAddressDto.builder()
                .id(entity.getId())
                .address(entity.getAddress())
                .city(entity.getCity())
                .state(entity.getState())
                .postalCode(entity.getPostalCode())
                .country(entity.getCountry())
                .permanentAddress(entity.getPermanentAddress())
                .permanentCity(entity.getPermanentCity())
                .permanentState(entity.getPermanentState())
                .permanentPostalCode(entity.getPermanentPostalCode())
                .permanentCountry(entity.getPermanentCountry())
                .build();
    }

    // =========================================================
    // 🔹 EMERGENCY CONTACT
    // =========================================================
    public EmployeeEmergencyContactDto toEmergencyDto(EmployeeEmergencyContact entity) {
        if (entity == null) return null;

        return EmployeeEmergencyContactDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .phone(entity.getPhone())
                .relation(entity.getRelation())
                .build();
    }

    // =========================================================
    // 🔹 BANK DETAILS
    // =========================================================
    public EmployeeBankDetailsDto toBankDto(EmployeeBankDetails entity) {
        if (entity == null) return null;

        return EmployeeBankDetailsDto.builder()
                .id(entity.getId())
                .bankName(entity.getBankName())
                .accountNumber(MaskingUtil.maskBankAccount(entity.getAccountNumber()))   // 🔒 masked
                .ifscCode(entity.getIfscCode())
                .build();
    }

    // =========================================================
    // 🔹 EMPLOYMENT DETAILS
    // =========================================================
    public EmployeeEmploymentDetailsDto toEmploymentDto(EmployeeEmploymentDetails entity) {
        if (entity == null) return null;

        return EmployeeEmploymentDetailsDto.builder()
                .id(entity.getId())
                .pfNumber(entity.getPfNumber())
                .esiNumber(entity.getEsiNumber())
                .build();
    }
}


package com.devix.employemanagement.Mappers.employeeMapper;

import com.devix.employemanagement.dtos.EmployeeDto.requestDto.*;
import com.devix.employemanagement.entities.Designation;
import com.devix.employemanagement.entities.Office;
import com.devix.employemanagement.entities.Organization;
import com.devix.employemanagement.entities.User.*;
import com.devix.employemanagement.utils.enums.EmploymentType;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class CreateEmployeeMapper {

    public Employee toEntity(EmployeeCreateDto dto, User user,
                             Organization org, Office office,
                             Designation designation) {

        return Employee.builder()
                .user(user)
                .organization(org)
                .employeeCode(dto.getEmployeeCode())
                .fullName(dto.getFullName())
                .department(dto.getDepartment())
                .designation(designation)
                .primaryOffice(office)
                .employmentType(EmploymentType.valueOf(dto.getEmploymentType()))
                .joiningDate(dto.getJoiningDate() != null ? dto.getJoiningDate() : LocalDate.now())
                .managerId(dto.getManagerId())
                .managerName(dto.getManagerName())
                .status("ACTIVE") // ✅ default
                .build();
    }

    public EmployeePersonalDetails toPersonal(EmployeeCreateDto dto, Employee emp) {
        PersonalRequestDto p = dto.getPersonal();
        if (p == null) return null;

        return EmployeePersonalDetails.builder()
                .employee(emp)
                .dateOfBirth(p.getDateOfBirth())
                .gender(p.getGender())
                .maritalStatus(p.getMaritalStatus())
                .bloodGroup(p.getBloodGroup())
                .panNumber(p.getPanNumber())
                .aadharNumber(p.getAadharNumber())
                .personalEmail(p.getPersonalEmail())
                .alternateMobileNumber(p.getAlternateMobileNumber())
                .build();
    }

    public EmployeeAddress toAddress(EmployeeCreateDto dto, Employee emp) {
        AddressRequestDto a = dto.getAddress();
        if (a == null) return null;

        return EmployeeAddress.builder()
                .employee(emp)
                .address(a.getAddress())
                .city(a.getCity())
                .state(a.getState())
                .postalCode(a.getPostalCode())
                .country(a.getCountry())
                .permanentAddress(a.getPermanentAddress())
                .permanentCity(a.getPermanentCity())
                .permanentState(a.getPermanentState())
                .permanentPostalCode(a.getPermanentPostalCode())
                .permanentCountry(a.getPermanentCountry())
                .build();
    }

    public EmployeeEmergencyContact toEmergency(EmployeeCreateDto dto, Employee emp) {
        EmergencyRequestDto e = dto.getEmergency();
        if (e == null) return null;

        return EmployeeEmergencyContact.builder()
                .employee(emp)
                .name(e.getName())
                .phone(e.getPhone())
                .relation(e.getRelation())
                .build();
    }

    public EmployeeBankDetails toBank(EmployeeCreateDto dto, Employee emp) {
        BankRequestDto b = dto.getBank();
        if (b == null) return null;

        return EmployeeBankDetails.builder()
                .employee(emp)
                .bankName(b.getBankName())
                .accountNumber(b.getAccountNumber())
                .ifscCode(b.getIfscCode())
                .build();
    }

    public EmployeeEmploymentDetails toEmployment(EmployeeCreateDto dto, Employee emp) {
        EmploymentRequestDto e = dto.getEmployment();
        if (e == null) return null;

        return EmployeeEmploymentDetails.builder()
                .employee(emp)
                .pfNumber(e.getPfNumber())
                .esiNumber(e.getEsiNumber())
                .build();
    }
}

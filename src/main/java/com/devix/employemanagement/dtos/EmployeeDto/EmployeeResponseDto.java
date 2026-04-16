package com.devix.employemanagement.dtos.EmployeeDto;

import com.devix.employemanagement.entities.Designation;
import com.devix.employemanagement.utils.enums.EmploymentType;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponseDto {
    private Long id;
    private String employeeCode;
    private String fullName;
    private String phone;
    private Designation designation;
    private String department;
    private String profileImageUrl;
    private LocalDate joiningDate;
    private String status;
    private Long userId;
    private Long organizationId;
    private Long primaryOfficeId;
    private String organizationName;
    private String primaryOfficeName;
    private EmploymentType employmentType;
    private EmployeePersonalDetailsDto personalDetails;
    private EmployeeAddressDto employeeAddress;
    private EmployeeEmergencyContactDto emergencyContact;
    private EmployeeBankDetailsDto  employeeBankDetails;
    private EmployeeEmploymentDetailsDto employeeEmploymentDetails;
    private Long managerId;
    private String managerName;
}

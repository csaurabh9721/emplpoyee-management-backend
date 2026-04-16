package com.devix.employemanagement.services.employeeService;

import com.devix.employemanagement.Mappers.EmployeeMapper;
import com.devix.employemanagement.dtos.EmployeeDto.EmployeeRequestDto;
import com.devix.employemanagement.dtos.EmployeeDto.EmployeeResponseDto;
import com.devix.employemanagement.dtos.EmployeeDto.EmployeeUpdateProfileDto;
import com.devix.employemanagement.entities.*;
import com.devix.employemanagement.entities.User.*;
import com.devix.employemanagement.exceptions.BadRequestException;
import com.devix.employemanagement.exceptions.ResourceNotFoundException;
import com.devix.employemanagement.repo.*;
import com.devix.employemanagement.repo.userRepo.*;
import jakarta.transaction.Transactional;
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
    private final EmployeePersonalDetailsRepo personalRepo;
    private final EmployeeAddressRepo addressRepo;
    private final EmployeeEmergencyContactRepo emergencyRepo;
    private final EmployeeBankDetailsRepo bankRepo;
    private final EmployeeEmploymentDetailsRepo employmentRepo;

    @Transactional
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

        // ✅ 1. Save Employee
        Employee emp = employeeMapper.toEntity(dto, user, org, office, designation);
        emp = employeeRepository.save(emp);

        // ✅ 2. Save Personal
        EmployeePersonalDetails personal = personalRepo.save(
                employeeMapper.toPersonal(dto, emp)
        );

        // ✅ 3. Save Address
        EmployeeAddress address = addressRepo.save(
                employeeMapper.toAddress(dto, emp)
        );

        // ✅ 4. Save Emergency
        EmployeeEmergencyContact emergency = emergencyRepo.save(
                employeeMapper.toEmergency(dto, emp)
        );

        // ✅ 5. Save Bank
        EmployeeBankDetails bank = bankRepo.save(
                employeeMapper.toBank(dto, emp)
        );

        // ✅ 6. Save Employment
        EmployeeEmploymentDetails employment = employmentRepo.save(
                employeeMapper.toEmployment(dto, emp)
        );

        // ✅ 7. Return FULL DTO
        return employeeMapper.toDto(emp, address, bank, emergency, employment, personal);
    }

    @Transactional()
    public List<EmployeeResponseDto> getAll() {

        return employeeRepository.findAll()
                .stream()
                .map(emp -> {

                    EmployeePersonalDetails personal = personalRepo.findByEmployee(emp).orElse(null);
                    EmployeeAddress address = addressRepo.findByEmployee(emp).orElse(null);
                    EmployeeEmergencyContact emergency = emergencyRepo.findByEmployee(emp).orElse(null);
                    EmployeeBankDetails bank = bankRepo.findByEmployee(emp).orElse(null);
                    EmployeeEmploymentDetails employment = employmentRepo.findByEmployee(emp).orElse(null);

                    return employeeMapper.toDto(emp, address, bank, emergency, employment, personal);
                })
                .toList();
    }

    public void delete(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Employee not found");
        }
        employeeRepository.deleteById(id);
    }


    @Transactional()
    public EmployeeResponseDto getById(Long id) {

        Employee emp = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        EmployeePersonalDetails personal = personalRepo.findByEmployee(emp).orElse(null);
        EmployeeAddress address = addressRepo.findByEmployee(emp).orElse(null);
        EmployeeEmergencyContact emergency = emergencyRepo.findByEmployee(emp).orElse(null);
        EmployeeBankDetails bank = bankRepo.findByEmployee(emp).orElse(null);
        EmployeeEmploymentDetails employment = employmentRepo.findByEmployee(emp).orElse(null);

        return employeeMapper.toDto(emp, address, bank, emergency, employment, personal);
    }


    @Transactional
    public EmployeeResponseDto updateProfile(EmployeeUpdateProfileDto dto) {

        Employee emp = employeeRepository.findById(dto.getEmployeeId())
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        // ✅ 1. Update Employee (ONLY allowed fields)
        emp.setFullName(dto.getFullName());
        emp = employeeRepository.save(emp);

        // ✅ 2. Personal Details
        EmployeePersonalDetails personal = personalRepo.findByEmployee(emp)
                .orElse(new EmployeePersonalDetails());

        personal.setEmployee(emp);
        personal.setPersonalEmail(dto.getPersonalEmail());
        personal.setAlternateMobileNumber(dto.getAlternateMobileNumber());
        personal.setGender(dto.getGender());
        personal.setMaritalStatus(dto.getMaritalStatus());
        personal.setBloodGroup(dto.getBloodGroup());

        personal = personalRepo.save(personal);

        // ✅ 3. Address
        EmployeeAddress address = addressRepo.findByEmployee(emp)
                .orElse(new EmployeeAddress());

        address.setEmployee(emp);
        address.setAddress(dto.getAddress());
        address.setCity(dto.getCity());
        address.setState(dto.getState());
        address.setPostalCode(dto.getPostalCode());
        address.setCountry(dto.getCountry());

        // Permanent
        address.setPermanentAddress(dto.getPermanentAddress());
        address.setPermanentCity(dto.getPermanentCity());
        address.setPermanentState(dto.getPermanentState());
        address.setPermanentPostalCode(dto.getPermanentPostalCode());
        address.setPermanentCountry(dto.getPermanentCountry());

        address = addressRepo.save(address);

        // ✅ 4. Emergency Contact
        EmployeeEmergencyContact emergency = emergencyRepo.findByEmployee(emp)
                .orElse(new EmployeeEmergencyContact());

        emergency.setEmployee(emp);
        emergency.setName(dto.getEmergencyName());
        emergency.setPhone(dto.getEmergencyPhone());
        emergency.setRelation(dto.getEmergencyRelation());

        emergency = emergencyRepo.save(emergency);

        // ✅ 5. Fetch other existing data
        EmployeeBankDetails bank = bankRepo.findByEmployee(emp).orElse(null);
        EmployeeEmploymentDetails employment = employmentRepo.findByEmployee(emp).orElse(null);

        // ✅ 6. Return updated response
        return employeeMapper.toDto(emp, address, bank, emergency, employment, personal);
    }

}


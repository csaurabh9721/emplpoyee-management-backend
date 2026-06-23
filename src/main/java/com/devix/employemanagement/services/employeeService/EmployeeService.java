package com.devix.employemanagement.services.employeeService;

import com.devix.employemanagement.Mappers.employeeMapper.CreateEmployeeMapper;
import com.devix.employemanagement.Mappers.employeeMapper.EmployeeMapper;
import com.devix.employemanagement.dtos.EmployeeDto.requestDto.EmployeeCreateDto;
import com.devix.employemanagement.dtos.EmployeeDto.responseDto.EmployeeResponseDto;
import com.devix.employemanagement.dtos.EmployeeDto.requestDto.EmployeeUpdateProfileDto;
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
public class EmployeeService implements IEmployeeService {

    private final EmployeeRepository employeeRepository;
    private final UserRepository userRepository;
    private final OrganizationRepo organizationRepository;
    private final OfficeRepo officeRepository;
    private final EmployeeMapper employeeMapper;
    private final CreateEmployeeMapper createEmployeeMapper;
    private final DesignationRepo designationRepo;
    private final EmployeePersonalDetailsRepo personalRepo;
    private final EmployeeAddressRepo addressRepo;
    private final EmployeeEmergencyContactRepo emergencyRepo;
    private final EmployeeBankDetailsRepo bankRepo;
    private final EmployeeEmploymentDetailsRepo employmentRepo;

    @Override
    @Transactional
    public EmployeeResponseDto create(EmployeeCreateDto dto) {

        if (employeeRepository.existsByEmployeeCode(dto.getEmployeeCode())) {
            throw new BadRequestException("Employee code already exists");
        }

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Organization org = organizationRepository.findById(dto.getOrganizationId())
                .orElseThrow(() -> new ResourceNotFoundException("Organization not found"));

        Office office = dto.getPrimaryOfficeId() != null
                ? officeRepository.findById(dto.getPrimaryOfficeId())
                .orElseThrow(() -> new ResourceNotFoundException("Office not found"))
                : null;

        Designation designation = designationRepo.findById(dto.getDesignationId())
                .orElseThrow(() -> new ResourceNotFoundException("Designation not found"));

        // ✅ Employee
        Employee emp = createEmployeeMapper.toEntity(dto, user, org, office, designation);
        emp = employeeRepository.save(emp);

        // ✅ Sub Entities
        EmployeePersonalDetails personal = personalRepo.save(createEmployeeMapper.toPersonal(dto, emp));
        EmployeeAddress address = addressRepo.save(createEmployeeMapper.toAddress(dto, emp));
        EmployeeEmergencyContact emergency = emergencyRepo.save(createEmployeeMapper.toEmergency(dto, emp));
        EmployeeBankDetails bank = bankRepo.save(createEmployeeMapper.toBank(dto, emp));
        EmployeeEmploymentDetails employment = employmentRepo.save(createEmployeeMapper.toEmployment(dto, emp));

        return employeeMapper.toDto(emp, address, bank, emergency, employment, personal);
    }

    @Override
    @Transactional
    public List<EmployeeResponseDto> getAll() {

        return employeeRepository.findAll()
                .stream()
                .map(emp -> {
                    EmployeePersonalDetails personal = personalRepo.findByEmployee(emp).orElseThrow(()->new ResourceNotFoundException("Personal details not found for employee: " + emp.getId()));
                    EmployeeAddress address = addressRepo.findByEmployee(emp).orElseThrow(()->new ResourceNotFoundException("Address details not found for employee: " + emp.getId()));
                    EmployeeEmergencyContact emergency = emergencyRepo.findByEmployee(emp).orElseThrow(()->new ResourceNotFoundException("Emergency contact details not found for employee: " + emp.getId()));
                    EmployeeBankDetails bank = bankRepo.findByEmployee(emp).orElseThrow(()->new ResourceNotFoundException("Bank details not found for employee: " + emp.getId()));
                    EmployeeEmploymentDetails employment = employmentRepo.findByEmployee(emp).orElseThrow(()->new ResourceNotFoundException("Employment details not found for employee: " + emp.getId()));
                    return employeeMapper.toDto(emp, address, bank, emergency, employment, personal);
                })
                .toList();
    }

    @Override
    public void delete(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Employee not found");
        }
        employeeRepository.deleteById(id);
    }


    @Override
    @Transactional()
    public EmployeeResponseDto getById(Long id) {

        Employee emp = employeeRepository.findByIdWithDetails(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

//        Employee emp = employeeRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
//
//        EmployeePersonalDetails personal = personalRepo.findByEmployee(emp).orElse(null);
//        EmployeeAddress address = addressRepo.findByEmployee(emp).orElse(null);
//        EmployeeEmergencyContact emergency = emergencyRepo.findByEmployee(emp).orElse(null);
//        EmployeeBankDetails bank = bankRepo.findByEmployee(emp).orElse(null);
//        EmployeeEmploymentDetails employment = employmentRepo.findByEmployee(emp).orElse(null);
        return employeeMapper.toDto(emp);
    }


    @Override
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


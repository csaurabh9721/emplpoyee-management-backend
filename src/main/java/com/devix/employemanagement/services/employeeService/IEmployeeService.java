package com.devix.employemanagement.services.employeeService;

import com.devix.employemanagement.dtos.EmployeeDto.requestDto.EmployeeCreateDto;
import com.devix.employemanagement.dtos.EmployeeDto.requestDto.EmployeeUpdateProfileDto;
import com.devix.employemanagement.dtos.EmployeeDto.responseDto.EmployeeResponseDto;

import java.util.List;

public interface IEmployeeService {

    EmployeeResponseDto create(EmployeeCreateDto dto);

    List<EmployeeResponseDto> getAll();

    void delete(Long id);

    EmployeeResponseDto getById(Long id);

    EmployeeResponseDto updateProfile(EmployeeUpdateProfileDto dto);
}

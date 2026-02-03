package com.devix.employemanagement.services.officeService;

import com.devix.employemanagement.dtos.OfficeRequestDto;
import com.devix.employemanagement.dtos.OfficeResponseDto;

import java.util.List;

public interface OfficeService {
    OfficeResponseDto getOffice(Long id);

    List<OfficeResponseDto> getAllOffices();

    OfficeResponseDto saveOffice(OfficeRequestDto office);

    OfficeResponseDto updateOffice(OfficeRequestDto office,Long id);

    void deleteOffice(String id);
}

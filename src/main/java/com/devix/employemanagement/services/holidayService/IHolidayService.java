package com.devix.employemanagement.services.holidayService;

import com.devix.employemanagement.dtos.holidayDtos.HolidayResponseDTO;

import java.util.List;

public interface IHolidayService {

    List<HolidayResponseDTO> getHolidayByOfficeId(Long officeId);
}

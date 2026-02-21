package com.devix.employemanagement.services.holidayService;


import com.devix.employemanagement.Mappers.HolidayMapper;
import com.devix.employemanagement.dtos.holidayDtos.HolidayResponseDTO;
import com.devix.employemanagement.repo.HolidayYearRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HolidayService  {

    private final HolidayYearRepository holidayYearRepository;

    public List<HolidayResponseDTO> getHolidayByOfficeId(Long officeId) {

        int currentYear = LocalDate.now().getYear();

        return holidayYearRepository
                .findByOfficeIdAndYear(officeId, currentYear)
                .stream()
                .map(HolidayMapper::toDTO)
                .collect(Collectors.toList());
    }
}
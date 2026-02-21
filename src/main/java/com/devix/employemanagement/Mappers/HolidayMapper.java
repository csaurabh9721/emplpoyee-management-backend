package com.devix.employemanagement.Mappers;

import com.devix.employemanagement.dtos.holidayDtos.HolidayResponseDTO;
import com.devix.employemanagement.entities.Holiday;

public class HolidayMapper {

    public static HolidayResponseDTO toDTO(Holiday holiday) {
        return HolidayResponseDTO.builder()
                .id(holiday.getId())
                .name(holiday.getName())
                .date(holiday.getDate())
                .optionalHoliday(holiday.isOptionalHoliday())
                .officeId(
                        holiday.getOffice() != null
                                ? holiday.getOffice().getId()
                                : null
                )
                .officeName(
                        holiday.getOffice() != null
                                ? holiday.getOffice().getName()
                                : null
                )
                .build();
    }
}
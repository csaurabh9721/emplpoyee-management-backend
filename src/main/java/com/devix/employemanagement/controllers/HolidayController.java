package com.devix.employemanagement.controllers;


import com.devix.employemanagement.dtos.ApiResponse;
import com.devix.employemanagement.dtos.holidayDtos.HolidayResponseDTO;
import com.devix.employemanagement.services.holidayService.HolidayService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/holidays")
@RequiredArgsConstructor
public class HolidayController {

    private final HolidayService holidayYearService;

    @GetMapping("/getAll/{officeId}")
    public ResponseEntity<ApiResponse<List<HolidayResponseDTO>>> getHolidayByOfficeId(
            @PathVariable Long officeId
    ) {
        final List<HolidayResponseDTO> returnedData = holidayYearService.getHolidayByOfficeId(officeId);
        ApiResponse<List<HolidayResponseDTO>> apiResponse = new ApiResponse<>(200, "Fetched successfully.", returnedData);
        return ResponseEntity.ok(apiResponse);
    }
}
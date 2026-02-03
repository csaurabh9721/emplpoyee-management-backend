package com.devix.employemanagement.controllers;


import com.devix.employemanagement.dtos.ApiResponse;
import com.devix.employemanagement.dtos.OfficeRequestDto;
import com.devix.employemanagement.dtos.OfficeResponseDto;
import com.devix.employemanagement.entities.Office;
import com.devix.employemanagement.services.officeService.OfficeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/office")
public class OfficeController {
    final private OfficeService officeService;

    public OfficeController(OfficeService officeService) {
        this.officeService = officeService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse<List<OfficeResponseDto>>> requestAllOffices() {
        try {
            List<OfficeResponseDto> allOffices = officeService.getAllOffices();
            ApiResponse<List<OfficeResponseDto>> apiResponse = new ApiResponse<>(200, "Fetched Successfully", allOffices);
            return ResponseEntity.ok(apiResponse);
        } catch (Exception ex) {
            ApiResponse<List<OfficeResponseDto>> apiResponse = new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
        }
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<ApiResponse<OfficeResponseDto>> getById(@PathVariable Long id) {
        try {
            OfficeResponseDto office = officeService.getOffice(id);
            ApiResponse<OfficeResponseDto> apiResponse = new ApiResponse<>(200, "Fetched Successfully", office);
            return ResponseEntity.ok(apiResponse);
        } catch (Exception ex) {
            ApiResponse<OfficeResponseDto> apiResponse = new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<OfficeResponseDto>> addOffice(@RequestBody OfficeRequestDto officeRequestDto) {
        try {
            OfficeResponseDto office = officeService.saveOffice(officeRequestDto);
            ApiResponse<OfficeResponseDto> apiResponse = new ApiResponse<>(200, "Saved Successfully", office);
            return ResponseEntity.ok(apiResponse);
        } catch (Exception ex) {
            ApiResponse<OfficeResponseDto> apiResponse = new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<OfficeResponseDto>> updateOffice(@RequestBody OfficeRequestDto officeRequestDto,@PathVariable Long id) {
        try {
            OfficeResponseDto office = officeService.updateOffice(officeRequestDto,id);
            ApiResponse<OfficeResponseDto> apiResponse = new ApiResponse<>(200, "Saved Successfully", office);
            return ResponseEntity.ok(apiResponse);
        } catch (Exception ex) {
            ApiResponse<OfficeResponseDto> apiResponse = new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
        }
    }

}

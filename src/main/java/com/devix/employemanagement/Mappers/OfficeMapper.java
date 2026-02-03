package com.devix.employemanagement.Mappers;

import com.devix.employemanagement.dtos.OfficeRequestDto;
import com.devix.employemanagement.dtos.OfficeResponseDto;
import com.devix.employemanagement.entities.Office;
import com.devix.employemanagement.entities.Organization;

public class OfficeMapper {

    public OfficeResponseDto entityToDto(Office office) {
        OfficeResponseDto officeResponseDto = new OfficeResponseDto();
        officeResponseDto.setId(office.getId());
        officeResponseDto.setOrganization(office.getOrganization());
        officeResponseDto.setName(office.getName());
        officeResponseDto.setLongitude(office.getLongitude());
        officeResponseDto.setLatitude(office.getLatitude());
        officeResponseDto.setAddress(office.getAddress());
        officeResponseDto.setActive(office.getActive());
        return officeResponseDto;
    }

    public Office requestDtoToEntity(OfficeRequestDto dto, Organization organization) {
        Office entity = new Office();
        entity.setOrganization(organization);
        entity.setName(dto.getName());
        entity.setLongitude(dto.getLongitude());
        entity.setLatitude(dto.getLatitude());
        entity.setAddress(dto.getAddress());
        entity.setActive(dto.getActive());
        return entity;
    }
}

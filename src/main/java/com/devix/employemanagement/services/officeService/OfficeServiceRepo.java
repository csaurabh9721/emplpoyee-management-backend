package com.devix.employemanagement.services.officeService;

import com.devix.employemanagement.Mappers.OfficeMapper;
import com.devix.employemanagement.dtos.OfficeRequestDto;
import com.devix.employemanagement.dtos.OfficeResponseDto;
import com.devix.employemanagement.entities.Office;
import com.devix.employemanagement.entities.Organization;
import com.devix.employemanagement.repo.OfficeRepo;
import com.devix.employemanagement.repo.OrganizationRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfficeServiceRepo implements OfficeService {

    private final OfficeRepo officeRepo;
    private final OrganizationRepo organizationRepo;
    private final OfficeMapper officeMapper = new OfficeMapper();

    public OfficeServiceRepo(OfficeRepo officeRepo, OrganizationRepo organizationRepo) {
        this.officeRepo = officeRepo;
        this.organizationRepo = organizationRepo;
    }

    @Override
    public OfficeResponseDto getOffice(Long id) {
        Office office = officeRepo.findById(id).orElseThrow(()->new RuntimeException("Office not found"));
        return officeMapper.entityToDto(office);
    }

    @Override
    public List<OfficeResponseDto> getAllOffices() {
        List<Office> offices = officeRepo.findAll();
        return offices.stream().map(officeMapper::entityToDto).collect(Collectors.toList());
    }

    @Override
    public OfficeResponseDto saveOffice(OfficeRequestDto office) {
        Organization organization = organizationRepo.findById(office.getOrganizationId()).orElseThrow(() -> new RuntimeException("Organization Not Found"));
        Office officeToSave = officeMapper.requestDtoToEntity(office,organization);
        Office savedOffice = officeRepo.save(officeToSave);
        return officeMapper.entityToDto(savedOffice);
    }

    @Override
    public OfficeResponseDto updateOffice(OfficeRequestDto officeRequestDto, Long id) {
        Organization organization = organizationRepo.findById(officeRequestDto.getOrganizationId()).orElseThrow(() -> new RuntimeException("Organization Not Found"));
        Office office = officeRepo.findById(id).orElseThrow(()->new RuntimeException("Office not found"));
        office.setName(officeRequestDto.getName());
        office.setLatitude(officeRequestDto.getLatitude());
        office.setLongitude(officeRequestDto.getLongitude());
        office.setOrganization(organization);
        office.setAddress(officeRequestDto.getAddress());
        office.setActive(officeRequestDto.getActive());
        Office savedOffice = officeRepo.save(office);
        return officeMapper.entityToDto(savedOffice);
    }

    @Override
    public void deleteOffice(String id) {

    }
}

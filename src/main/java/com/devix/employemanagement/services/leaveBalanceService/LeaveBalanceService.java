package com.devix.employemanagement.services.leaveBalanceService;

import com.devix.employemanagement.Mappers.LeaveBalanceMapper;
import com.devix.employemanagement.dtos.leaveBalanceDto.LeaveBalanceResponseDto;
import com.devix.employemanagement.entities.LeaveBalance;
import com.devix.employemanagement.repo.LeaveBalanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LeaveBalanceService {
    private final  LeaveBalanceRepository leaveBalanceRepository;
    private final  LeaveBalanceMapper leaveBalanceMapper;


    public  List<LeaveBalanceResponseDto> getLeaveBalanceByEmployeeId(Long employeeId) {
        List<LeaveBalance> leaveBalances = leaveBalanceRepository.findByEmployeeIdForCurrentYear(employeeId, LocalDate.now().getYear());
        return leaveBalances.stream().map(leaveBalanceMapper::toDto).collect(Collectors.toList());

    }
}

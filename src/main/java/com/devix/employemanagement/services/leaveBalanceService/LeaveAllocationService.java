package com.devix.employemanagement.services.leaveBalanceService;

import com.devix.employemanagement.entities.Employee;
import com.devix.employemanagement.entities.LeaveBalance;
import com.devix.employemanagement.utils.LeaveType;
import com.devix.employemanagement.repo.EmployeeRepository;
import com.devix.employemanagement.repo.LeaveBalanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LeaveAllocationService {

    private final EmployeeRepository employeeRepository;
    private final LeaveBalanceRepository leaveBalanceRepository;

    public void allocateLeavesForNewYear(int year) {

        List<Employee> employees = employeeRepository.findAll();

        for (Employee employee : employees) {

            for (LeaveType type : LeaveType.values()) {

                boolean exists =
                        leaveBalanceRepository.existsByEmployeeIdAndLeaveTypeAndYear(
                                employee.getId(), type, year
                        );

                if (!exists) {

                    int total = getDefaultLeave(type);

                    LeaveBalance leaveBalance = LeaveBalance.builder()
                            .employee(employee)
                            .leaveType(type)
                            .totalAllowed(total)
                            .used(0)
                            .year(year)
                            .build();

                    leaveBalanceRepository.save(leaveBalance);
                }
            }
        }
    }

    private int getDefaultLeave(LeaveType type) {
        switch (type) {
            case EL:
                return 15;
            case CL:
                return 10;
            case RH:
                return 12;
            default:
                throw new IllegalArgumentException("Unknown leave type: " + type);
        }
    }
}


package com.devix.employemanagement.services.leaveBalanceService;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class LeaveBalanceScheduler {

    private final LeaveAllocationService leaveAllocationService;

    // Runs every year on 1 January at 00:00
  //  @Scheduled(cron = "0 */1 * * * *") // every 1 minute
    @Scheduled(cron = "0 0 0 1 1 *")
    public void runYearlyLeaveAllocation() {

        int currentYear = LocalDate.now().getYear();

        leaveAllocationService.allocateLeavesForNewYear(currentYear);
    }
}


package com.devix.employemanagement.repo;

import com.devix.employemanagement.entities.LeaveBalance;
import com.devix.employemanagement.utils.enums.LeaveType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveBalanceRepository  extends JpaRepository<LeaveBalance,Long> {

    @Query("SELECT l from LeaveBalance l where l.employee.id= :employeeId AND l.year = :year")
    List<LeaveBalance> findByEmployeeIdForCurrentYear(@Param("employeeId") Long employeeId,@Param("year") Integer year);

    boolean existsByEmployeeIdAndLeaveTypeAndYear(
            Long employeeId,
            LeaveType leaveType,
            Integer year
    );
}

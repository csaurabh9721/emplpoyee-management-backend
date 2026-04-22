package com.devix.employemanagement.repo;

import com.devix.employemanagement.entities.LeaveRequest;
import com.devix.employemanagement.utils.enums.LeaveStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {
    List<LeaveRequest> findByEmployeeId(Long id);
    List<LeaveRequest> findByApprovedByAndStatus(Long approvedBy, LeaveStatus status);

    @Query("""
    SELECT COUNT(l) > 0 FROM LeaveRequest l
    WHERE l.employee.id = :employeeId
    AND l.status IN :statuses
    AND l.startDate <= :endDate
    AND l.endDate >= :startDate
""")
    boolean existsOverlappingLeave(@Param("employeeId") Long employeeId,
                                   @Param("startDate") LocalDate startDate,
                                   @Param("endDate") LocalDate endDate,
                                   @Param("statuses") List<LeaveStatus> statuses);
}

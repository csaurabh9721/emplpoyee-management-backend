package com.devix.employemanagement.repo;


import com.devix.employemanagement.entities.AttendanceDaily;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AttendanceDailyRepository extends JpaRepository<AttendanceDaily, Long> {
    // Add custom query methods if needed
    List<AttendanceDaily> findByEmployeeId(Long employeeId);
    Optional<AttendanceDaily> findByEmployeeIdAndAttendanceDate(
            Long employeeId,
            LocalDate attendanceDate
    );
}
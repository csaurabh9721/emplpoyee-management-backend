package com.devix.employemanagement.repo;


import com.devix.employemanagement.entities.AttendanceDaily;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    @Query("SELECT a FROM AttendanceDaily a " +
            "WHERE a.employee.id = :employeeId " +
            "AND a.attendanceDate BETWEEN :startDate AND :endDate")
    List<AttendanceDaily> findByEmployeeIdAndAttendanceDateBetween(
            @Param("employeeId") Long employeeId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );

    @Query("SELECT a FROM AttendanceDaily a " +
            "WHERE a.employee.id = :employeeId " +
            "AND a.attendanceDate BETWEEN :startDate AND :endDate")
    List<AttendanceDaily> findByManagerIdAndAttendanceDateBetween(
            @Param("employeeId") Long employeeId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );
}
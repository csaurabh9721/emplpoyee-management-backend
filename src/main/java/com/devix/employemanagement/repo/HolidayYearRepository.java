package com.devix.employemanagement.repo;

import com.devix.employemanagement.entities.Holiday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface HolidayYearRepository extends JpaRepository<Holiday, Long> {

    @Query("""
                SELECT h FROM Holiday h
                WHERE h.office.id = :officeId
                AND YEAR(h.date) = :year
                ORDER BY h.date ASC
            """)
    List<Holiday> findByOfficeIdAndYear(
            @Param("officeId") Long officeId,
            @Param("year") int year
    );

    @Query("SELECT h FROM Holiday h " +
            "WHERE h.date BETWEEN :startDate AND :endDate")
    List<Holiday> findByDateBetween(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );
}

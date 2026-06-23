package com.devix.employemanagement.repo.userRepo;

import com.devix.employemanagement.entities.User.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    boolean existsByEmployeeCode(String employeeCode);

    @Query("SELECT e FROM Employee e WHERE  e.user.id=:userId")
    Employee findByUserId(@Param("userId") String userId);

    List<Employee> findByManagerId(Long managerId);


    @Query("""
            select e from Employee e
            left join fetch e.personalDetails
            left join fetch e.address
            left join fetch e.emergencyContact
            left join fetch e.bankDetails
            left join fetch e.employmentDetails
            where e.id = :id
            """)
    Optional<Employee> findByIdWithDetails(Long id);
}

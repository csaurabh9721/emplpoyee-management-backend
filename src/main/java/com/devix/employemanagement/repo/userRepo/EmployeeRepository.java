package com.devix.employemanagement.repo.userRepo;

import com.devix.employemanagement.entities.User.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    boolean existsByEmployeeCode(String employeeCode);

    @Query("SELECT e FROM Employee e WHERE  e.user.id=:userId")
    Employee findByUserId(@Param("userId") String userId);
}

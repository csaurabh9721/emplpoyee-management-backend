package com.devix.employemanagement.repo.userRepo;

import com.devix.employemanagement.entities.User.Employee;
import com.devix.employemanagement.entities.User.EmployeeEmploymentDetails;
import com.devix.employemanagement.entities.User.EmployeePersonalDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeEmploymentDetailsRepo extends JpaRepository<EmployeeEmploymentDetails, Long> {
    Optional<EmployeeEmploymentDetails> findByEmployee(Employee employee);

}

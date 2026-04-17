package com.devix.employemanagement.repo.userRepo;

import com.devix.employemanagement.entities.User.Employee;
import com.devix.employemanagement.entities.User.EmployeeBankDetails;
import com.devix.employemanagement.entities.User.EmployeePersonalDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeBankDetailsRepo extends JpaRepository<EmployeeBankDetails, Long> {
    Optional<EmployeeBankDetails> findByEmployee(Employee employee);

}

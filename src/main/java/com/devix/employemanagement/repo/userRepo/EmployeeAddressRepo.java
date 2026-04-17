package com.devix.employemanagement.repo.userRepo;

import com.devix.employemanagement.entities.User.Employee;
import com.devix.employemanagement.entities.User.EmployeeAddress;
import com.devix.employemanagement.entities.User.EmployeePersonalDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeAddressRepo extends JpaRepository<EmployeeAddress, Long> {
    Optional<EmployeeAddress> findByEmployee(Employee employee);

}
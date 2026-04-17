package com.devix.employemanagement.repo;


import com.devix.employemanagement.entities.Designation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DesignationRepo extends JpaRepository<Designation, Long> {
}

package com.devix.employemanagement.repo;

import com.devix.employemanagement.entities.Office;
import com.devix.employemanagement.entities.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfficeRepo extends JpaRepository<Office, Long> {
}

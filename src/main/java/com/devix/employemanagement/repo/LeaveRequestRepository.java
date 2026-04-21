package com.devix.employemanagement.repo;

import com.devix.employemanagement.entities.LeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {
}

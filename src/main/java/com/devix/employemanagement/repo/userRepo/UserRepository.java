package com.devix.employemanagement.repo.userRepo;

import com.devix.employemanagement.entities.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT u FROM User u WHERE u.email = :email")
    Optional<User> findByEmail(String email);
    Optional<User> findByMobile(String mobile);
}


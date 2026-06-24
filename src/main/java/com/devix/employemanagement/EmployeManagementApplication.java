package com.devix.employemanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

@EnableJpaAuditing
@SpringBootApplication
@EnableScheduling
public class EmployeManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmployeManagementApplication.class, args);
    }

}

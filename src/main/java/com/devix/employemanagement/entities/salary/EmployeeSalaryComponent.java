package com.devix.employemanagement.entities.salary;


import com.devix.employemanagement.entities.User.Employee;
import jakarta.persistence.*;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "employee_salary_component")
public class EmployeeSalaryComponent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne(optional = false)
    @JoinColumn(name = "component_id")
    private SalaryComponent component;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal value;
    // If FIXED → 30000
    // If PERCENTAGE → 40 (means 40%)

    private LocalDate effectiveFrom;

    // getters & setters
}

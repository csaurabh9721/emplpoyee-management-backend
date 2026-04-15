package com.devix.employemanagement.entities.salary;

import com.devix.employemanagement.utils.enums.CalculationType;
import jakarta.persistence.*;
import com.devix.employemanagement.utils.enums.ComponentType;


@Entity
@Table(name = "salary_component")
public class SalaryComponent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;
    // BASIC, HRA, PF, BONUS etc.

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ComponentType type;
    // EARNING or DEDUCTION

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CalculationType calculationType;
    // FIXED or PERCENTAGE

    @Column(nullable = false)
    private Boolean active = true;

    // getters & setters
}
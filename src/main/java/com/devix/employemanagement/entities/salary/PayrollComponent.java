package com.devix.employemanagement.entities.salary;

import com.devix.employemanagement.utils.ComponentType;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "payroll_component")
public class PayrollComponent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "payroll_id")
    private Payroll payroll;

    @Column(nullable = false)
    private String componentName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ComponentType type;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal amount;

    // getters & setters
}

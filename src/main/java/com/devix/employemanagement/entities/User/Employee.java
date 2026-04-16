package com.devix.employemanagement.entities.User;


import com.devix.employemanagement.entities.Designation;
import com.devix.employemanagement.entities.Office;
import com.devix.employemanagement.entities.Organization;
import com.devix.employemanagement.utils.enums.EmploymentType;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "employee")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id", nullable = false)
    private Organization organization;

    @Column(nullable = false, unique = true)
    private String employeeCode;

    @Column(nullable = false)
    private String fullName;

    private String department;

    private String profileImageUrl;

    @ManyToOne
    @JoinColumn(name = "designation_id")
    private Designation designation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "primary_office_id")
    private Office primaryOffice;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EmploymentType employmentType;

    private LocalDate joiningDate;

    private Long managerId;
    private String managerName;

    @Column(nullable = false)
    private String status; // ACTIVE / INACTIVE

    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }
}

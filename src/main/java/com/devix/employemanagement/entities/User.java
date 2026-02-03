package com.devix.employemanagement.entities;

import com.devix.employemanagement.RoleEnum;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id", nullable = false)
    private Organization organization;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(unique = true)
    private String mobile;

    @Column(nullable = false)
    private String passwordHash;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleEnum role; // EMPLOYEE / ADMIN

    @Column(nullable = false)
    private Boolean active = true;

    private LocalDateTime lastLoginAt;
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }
}
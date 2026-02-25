package com.devix.employemanagement.entities;

import com.devix.employemanagement.LeaveType;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Entity
@Table(
        name = "leave_balances",
        indexes = {
                @Index(name = "idx_employee_year", columnList = "employee_id, year")
        },
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"employee_id", "leave_type", "year"})
        }
)
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LeaveBalance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* ================= RELATIONS ================= */

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    /* ================= LEAVE INFO ================= */

    @Enumerated(EnumType.STRING)
    @Column(name = "leave_type", nullable = false, length = 30)
    private LeaveType leaveType;

    @Column(name = "total_allowed", nullable = false)
    private Integer totalAllowed;

    @Column(nullable = false)
    private Integer used;

    @Column(name = "year", nullable = false)
    private Integer year;

    /* ================= VALIDATION ================= */

    private void validateState() {

        // default used
        if (used == null) {
            used = 0;
        }

        // totalAllowed must be valid
        if (totalAllowed == null || totalAllowed < 0) {
            throw new IllegalArgumentException("Total allowed leave must be non-negative");
        }

        // used cannot exceed total
        if (used > totalAllowed) {
            throw new IllegalArgumentException("Used leave cannot exceed total allowed");
        }

        // auto-set year
        if (year == null) {
            year = LocalDate.now().getYear();
        }
    }

    @PrePersist
    @PreUpdate
    public void validate() {
        validateState();
    }

    /* ================= AUDIT ================= */

    @CreatedDate
    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
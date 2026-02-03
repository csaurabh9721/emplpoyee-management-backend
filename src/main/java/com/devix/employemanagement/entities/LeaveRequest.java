package com.devix.employemanagement.entities;

import com.devix.employemanagement.entities.Employee;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "leave_requests")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LeaveRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* ================= RELATIONS ================= */

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    /* ================= LEAVE DETAILS ================= */

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LeaveType leaveType;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Column(nullable = false)
    private Integer totalDays;

    @Column(nullable = false)
    private String status;
    // PENDING / APPROVED / REJECTED / CANCELLED

    private String reason;

    /* ================= AUDIT ================= */

    private LocalDateTime appliedAt;
    private LocalDateTime actionedAt;

    @PrePersist
    public void prePersist() {
        appliedAt = LocalDateTime.now();
        status = "PENDING";
    }
}

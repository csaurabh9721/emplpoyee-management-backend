package com.devix.employemanagement.entities.User;
import lombok.*;
import jakarta.persistence.*;

import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "employee_personal_details")
public class EmployeePersonalDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    private LocalDate dateOfBirth;
    private String gender;
    private String maritalStatus;
    private String bloodGroup;

    private String panNumber;
    private String aadharNumber;
}

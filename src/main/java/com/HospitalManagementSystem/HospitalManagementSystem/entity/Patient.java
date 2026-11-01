package com.HospitalManagementSystem.HospitalManagementSystem.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity 
@Data 
@Table(name = "patients")
public class Patient {
    @Id 
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @NotBlank 

    private String name;

    @Email 
    private String email;
    
    @Column(unique = true)
    private String phone;
    private String gender;
    private String address;
    private LocalDate dateOfBirth;
    private String bloodGroup;
    private LocalDateTime createdAt;
}

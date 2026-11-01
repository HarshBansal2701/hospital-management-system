package com.HospitalManagementSystem.HospitalManagementSystem.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter 
public class PatientResponse {
     private Long id;
    private String name;
    private String email;
    private String phone;
    private String gender;
    private String address;
    private LocalDate dateOfBirth;
    private String bloodGroup;
    private LocalDateTime createdAt;
}

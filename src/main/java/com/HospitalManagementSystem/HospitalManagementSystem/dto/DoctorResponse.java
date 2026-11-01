package com.HospitalManagementSystem.HospitalManagementSystem.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter 
public class DoctorResponse {
    
     private Long id;
 
    private String name;

    private String email;

    private String phone;

    private String specialization;

    private String licenseNumber;

    private Integer experienceYears;

    private LocalDateTime createdAt;
}

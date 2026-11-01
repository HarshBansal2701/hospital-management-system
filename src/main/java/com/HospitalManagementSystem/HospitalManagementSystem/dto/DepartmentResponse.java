package com.HospitalManagementSystem.HospitalManagementSystem.dto;

import java.util.Set;

import lombok.Getter;
import lombok.Setter;


@Getter 
@Setter 
public class DepartmentResponse {
    private Long id;
    private String name;
    private String description;
    private Set<DoctorSummaryResponse> doctors;
}

package com.HospitalManagementSystem.HospitalManagementSystem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter 
public class DepartmentRequest {
    @NotBlank 
    @Size(min = 3 , max = 20)
    private String name;

    @NotBlank 
    @Size(max = 200)
    private String description;
}

package com.HospitalManagementSystem.HospitalManagementSystem.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter 
public class DoctorRequest {
    
    @NotBlank(message = "Name Required.")
    @Size(min = 3 , max = 20)
    private String name;

    @NotBlank(message = "Email Required.")
    @Email(message = "Invalid Email.")
    private String email;

    @NotBlank(message = "Phone Number Required.")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")
    private String phone;

    @NotBlank(message = "Specialization Required.")
    private String specialization;

    @NotBlank(message = "License Number Required.")
    private String licenseNumber;

    @NotNull(message = "Experience is Required.")
    @Min(value = 0 , message = "Experience can not be negative.")
    @Max(value = 60, message = "Experience can not exceed 60yr.")
    private Integer experienceYears;
}

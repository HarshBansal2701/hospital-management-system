package com.HospitalManagementSystem.HospitalManagementSystem.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter 
@Setter 
public class PatientRequest {
    @NotBlank 
    private String name;

    @NotBlank 
    @Email (message = "Invalid Email")
    private String email;
    
    @NotBlank 
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")
    private String phone;

    @NotBlank 
    @Pattern(regexp = "^(MALE|FEMALE|OTHER)$", message = "Gender must be MALE, FEMALE, or OTHER")
    private String gender;

    @NotBlank 
    @Size(max = 200)
    private String address;

    @Past(message = "Invalid Date of Birth")
    private LocalDate dateOfBirth;

    @Pattern(regexp = "^(A|B|AB|O)[+-]$", message = "Invalid blood group. Allowed values are A+, A-, B+, B-, AB+, AB-, O+, O-")
    private String bloodGroup;

}

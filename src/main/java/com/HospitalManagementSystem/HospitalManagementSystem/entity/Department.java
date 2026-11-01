package com.HospitalManagementSystem.HospitalManagementSystem.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity 
@Getter 
@Setter 
@Table(name = "department")
public class Department {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;
    private String description;

    @ManyToMany(mappedBy = "departments")
    private Set<Doctor> doctors = new HashSet<>(); 

    public void removeDoctor(Doctor doctor){
        doctors.remove(doctor);
    }
}

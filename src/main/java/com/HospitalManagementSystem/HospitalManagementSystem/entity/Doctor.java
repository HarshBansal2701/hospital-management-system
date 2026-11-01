package com.HospitalManagementSystem.HospitalManagementSystem.entity;



import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;


@Entity
@Getter 
@Setter 
@Table(name = "doctor")
public class Doctor {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    private String name;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String phone;

    private String specialization;

    @Column(unique = true)
    private String licenseNumber;

    private Integer experienceYears;

    private LocalDateTime createdAt;

    @ManyToMany 
    @JoinTable(
        name = "doctor_department",
        joinColumns = @JoinColumn(name = "doctor_id"),
        inverseJoinColumns = @JoinColumn(name = "department_id")
    )
    private Set<Department> departments = new HashSet<>();


    public void removeDepartment(Department department) {
        departments.remove(department);
        department.removeDoctor(this);
    }
}

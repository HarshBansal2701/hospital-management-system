package com.HospitalManagementSystem.HospitalManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.HospitalManagementSystem.HospitalManagementSystem.entity.Patient;

public interface PatientReository extends JpaRepository<Patient, Long> {
    boolean existsByEmail(String email);

    boolean existsByEmailAndIdNot(String email, Long id);
}

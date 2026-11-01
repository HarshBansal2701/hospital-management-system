package com.HospitalManagementSystem.HospitalManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.HospitalManagementSystem.HospitalManagementSystem.entity.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long>{
    public boolean existsByLicenseNumber(String licenseNumber);

    public boolean existsByPhone(String phone);

    public boolean existsByEmail(String email);

    public boolean existsByEmailAndIdNot(String email, Long id);

    public boolean existsByPhoneAndIdNot(String phone, Long id);

    public boolean existsByLicenseNumberAndIdNot(String licenseNumber, Long id);
}

package com.HospitalManagementSystem.HospitalManagementSystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.HospitalManagementSystem.HospitalManagementSystem.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long>{
    public boolean existsByName(String name);

    public boolean existsByNameAndIdNot(String name , Long id);

    @Query("""
    SELECT d
    FROM Department d
    LEFT JOIN FETCH d.doctors
    WHERE d.id = :id
    """)
    Optional<Department> findByIdWithDoctors(@Param("id") Long id);
}

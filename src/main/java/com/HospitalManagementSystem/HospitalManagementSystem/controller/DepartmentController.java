package com.HospitalManagementSystem.HospitalManagementSystem.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.HospitalManagementSystem.HospitalManagementSystem.dto.DepartmentRequest;
import com.HospitalManagementSystem.HospitalManagementSystem.dto.DepartmentResponse;
import com.HospitalManagementSystem.HospitalManagementSystem.service.DepartmentService;

import jakarta.validation.Valid;

@RestController 
@RequestMapping("/api/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService){
        this.departmentService = departmentService;
    }
    
    @GetMapping("/")
    public ResponseEntity<List<DepartmentResponse>> getAllDepartments(){
        return new ResponseEntity<>(departmentService.getAllDepartments(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentResponse> getDepartmentById(@PathVariable Long id){
        return new ResponseEntity<>(departmentService.getDepartmentById(id) , HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DepartmentResponse>  createDepartment(@Valid @RequestBody DepartmentRequest dept){
        return new ResponseEntity<>(departmentService.createDepartment(dept) , HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartmentResponse> updateDepartment(@Valid @RequestBody DepartmentRequest dept , @PathVariable Long id){
        return new ResponseEntity<>(departmentService.updateDepartment(id, dept) , HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long id){
        departmentService.deleteDepartment(id);
        return ResponseEntity.noContent().build();
    }

}

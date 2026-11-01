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

import com.HospitalManagementSystem.HospitalManagementSystem.dto.DoctorRequest;
import com.HospitalManagementSystem.HospitalManagementSystem.dto.DoctorResponse;
import com.HospitalManagementSystem.HospitalManagementSystem.service.DoctorServices;

import jakarta.validation.Valid;

@RestController 
@RequestMapping("/api/doctors")
public class DoctorController {
    
    private final DoctorServices doctorServices;

    public DoctorController(DoctorServices doctorServices){
        this.doctorServices = doctorServices;
    }

    @PostMapping
    public ResponseEntity<DoctorResponse> createDoctor(@Valid @RequestBody  DoctorRequest doctor){
        return new ResponseEntity<>(doctorServices.createDoctor(doctor) , HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorResponse> getDoctorById(@PathVariable Long id){
        DoctorResponse d = doctorServices.getDoctorById(id);
        return new ResponseEntity<>(d , HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<DoctorResponse>> getAllDoctors(){
        List<DoctorResponse> docRes = doctorServices.getAllDoctor();

        return new ResponseEntity<>(docRes, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DoctorResponse> updateDoctor(@Valid @RequestBody DoctorRequest doctor ,@PathVariable Long id){
        DoctorResponse docRes = doctorServices.updateDoctor(id, doctor);
        return new ResponseEntity<>(docRes, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable Long id){
        doctorServices.deleteDoctor(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/departments/{departmentId}")
    public ResponseEntity<Void> assignDepartment(@PathVariable Long id , @PathVariable Long departmentId){
        doctorServices.assignDepartment(id, departmentId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}/departments/{departmentId}")
    public ResponseEntity<Void> removeDepartment(@PathVariable Long id , @PathVariable Long departmentId){
        doctorServices.removeDepartment(id, departmentId);
        return ResponseEntity.noContent().build();
    }
}

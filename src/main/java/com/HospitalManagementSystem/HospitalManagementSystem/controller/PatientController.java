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

import com.HospitalManagementSystem.HospitalManagementSystem.dto.PatientRequest;
import com.HospitalManagementSystem.HospitalManagementSystem.dto.PatientResponse;
import com.HospitalManagementSystem.HospitalManagementSystem.service.PatientServices;

import jakarta.validation.Valid;

@RestController 
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientServices patientServices;

    public PatientController(PatientServices patientServices){
        this.patientServices = patientServices;
    }

    
    @PostMapping
    public ResponseEntity<PatientResponse> createPatient(@Valid @RequestBody PatientRequest p){
        return new ResponseEntity<>(patientServices.createPatient(p), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientResponse> getPatient(@PathVariable Long id){
        return new ResponseEntity<>( patientServices.getPatientById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PatientResponse>> getAllPatients(){
        return new ResponseEntity<>(patientServices.getAllPatient(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientResponse> updatePatient(@Valid @RequestBody PatientRequest patient, @PathVariable Long id){
        return new ResponseEntity<>(patientServices.updatePatient(id, patient), HttpStatus.OK);
    }
    

    // @DeleteMapping("/{id}")
    // public ResponseEntity<String> deletePatient(@PathVariable Long id){
    //     return new ResponseEntity<>(patientServices.deletePatient(id), HttpStatus.NO_CONTENT);
    // }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
    patientServices.deletePatient(id);
    return ResponseEntity.noContent().build();
}
}

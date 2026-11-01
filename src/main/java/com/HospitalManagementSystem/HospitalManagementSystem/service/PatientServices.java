package com.HospitalManagementSystem.HospitalManagementSystem.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.HospitalManagementSystem.HospitalManagementSystem.dto.PatientRequest;
import com.HospitalManagementSystem.HospitalManagementSystem.dto.PatientResponse;
import com.HospitalManagementSystem.HospitalManagementSystem.entity.Patient;
import com.HospitalManagementSystem.HospitalManagementSystem.exception.PatientAlreadyExistException;
import com.HospitalManagementSystem.HospitalManagementSystem.exception.PatientNotFoundException;
import com.HospitalManagementSystem.HospitalManagementSystem.repository.PatientReository;

@Service 
public class PatientServices {
    
    private final PatientReository patientRepository;

    private PatientResponse mapToResponse(Patient p){

        PatientResponse pResponse = new PatientResponse();

        pResponse.setId(p.getId());
        pResponse.setName(p.getName());
        pResponse.setAddress(p.getAddress());
        pResponse.setBloodGroup(p.getBloodGroup());
        pResponse.setDateOfBirth(p.getDateOfBirth());
        pResponse.setEmail(p.getEmail());
        pResponse.setGender(p.getGender());
        pResponse.setCreatedAt(p.getCreatedAt());
        pResponse.setPhone(p.getPhone());

        return pResponse;
    }

    public PatientServices(PatientReository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public PatientResponse getPatientById(Long id){
        Patient patient = patientRepository.findById(id).orElseThrow(() -> new PatientNotFoundException("patient not found with id: " + id));
        return mapToResponse(patient);
    }

    public PatientResponse createPatient(PatientRequest p){

        if(patientRepository.existsByEmail(p.getEmail())){
            throw new PatientAlreadyExistException("Patient Already Exist with email: " + p.getEmail());
        }

        Patient patient = new Patient();
        patient.setName(p.getName());
        patient.setAddress(p.getAddress());
        patient.setBloodGroup(p.getBloodGroup());
        patient.setDateOfBirth(p.getDateOfBirth());
        patient.setGender(p.getGender());
        patient.setPhone(p.getPhone());
        patient.setEmail(p.getEmail());

        patient.setCreatedAt(LocalDateTime.now());
        Patient savePatient = patientRepository.save(patient);
        return mapToResponse(savePatient);
    }

    public List<PatientResponse> getAllPatient(){
        List<Patient> patientList =  patientRepository.findAll();

        List<PatientResponse> patientListResponse = new ArrayList<>();

        for(Patient p : patientList){
            patientListResponse.add(mapToResponse(p));
        }

        return patientListResponse;
    }

    public PatientResponse updatePatient(Long id , PatientRequest p){
        Patient patientExist = patientRepository.findById(id).orElseThrow(() -> new PatientNotFoundException("Patient not found with id: " + id));

        if(patientRepository.existsByEmailAndIdNot(p.getEmail(),id)){
            throw new PatientAlreadyExistException("Patient Already Exist with email: " + p.getEmail());
        }

        patientExist.setAddress(p.getAddress());
        patientExist.setBloodGroup(p.getBloodGroup());
        patientExist.setDateOfBirth(p.getDateOfBirth());
        patientExist.setEmail(p.getEmail());
        patientExist.setGender(p.getGender());
        patientExist.setName(p.getName());
        patientExist.setPhone(p.getPhone());

        Patient savedPatient = patientRepository.save(patientExist);

        return mapToResponse(savedPatient);
    }

    public void deletePatient(Long id){
        Patient patientExist = patientRepository.findById(id).orElseThrow(() -> new PatientNotFoundException("Patient not found with id: "+ id));

        patientRepository.delete(patientExist);
        // return "Patient Deleted Successfully";
    }

    
}

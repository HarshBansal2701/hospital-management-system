package com.HospitalManagementSystem.HospitalManagementSystem.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.HospitalManagementSystem.HospitalManagementSystem.dto.DoctorRequest;
import com.HospitalManagementSystem.HospitalManagementSystem.dto.DoctorResponse;
import com.HospitalManagementSystem.HospitalManagementSystem.entity.Department;
import com.HospitalManagementSystem.HospitalManagementSystem.entity.Doctor;
import com.HospitalManagementSystem.HospitalManagementSystem.exception.DepartmentNotFoundException;
import com.HospitalManagementSystem.HospitalManagementSystem.exception.DoctorAlreadyExistException;
import com.HospitalManagementSystem.HospitalManagementSystem.exception.DoctorDepartmentAlreadyExistsException;
import com.HospitalManagementSystem.HospitalManagementSystem.exception.DoctorDepartmentNotFoundException;
import com.HospitalManagementSystem.HospitalManagementSystem.exception.DoctorNotFoundException;
import com.HospitalManagementSystem.HospitalManagementSystem.repository.DepartmentRepository;
import com.HospitalManagementSystem.HospitalManagementSystem.repository.DoctorRepository;

@Service 
public class DoctorServices {
    
    private final DoctorRepository doctorRepository;
    private final DepartmentRepository departmentRepository;

    public DoctorServices(DoctorRepository doctorRepository , DepartmentRepository departmentRepository){
        this.doctorRepository = doctorRepository;
        this.departmentRepository = departmentRepository;
    }

    private DoctorResponse maptoResponse(Doctor d){
        DoctorResponse doctorResponse = new DoctorResponse();

        doctorResponse.setId(d.getId());
        doctorResponse.setName(d.getName());
        doctorResponse.setEmail(d.getEmail());
        doctorResponse.setLicenseNumber(d.getLicenseNumber());
        doctorResponse.setPhone(d.getPhone());
        doctorResponse.setSpecialization(d.getSpecialization());
        doctorResponse.setExperienceYears(d.getExperienceYears());
        doctorResponse.setCreatedAt(d.getCreatedAt());
        
        return doctorResponse;
    }

    public DoctorResponse createDoctor(DoctorRequest d){

        if(doctorRepository.existsByEmail(d.getEmail())){
            throw new DoctorAlreadyExistException("Doctor Already Exist with Email: " + d.getEmail());
        }
        if(doctorRepository.existsByLicenseNumber(d.getLicenseNumber())){
            throw new DoctorAlreadyExistException("Doctor Already Exist with License Number: " + d.getLicenseNumber());
        }
        if(doctorRepository.existsByPhone(d.getPhone())){
            throw new DoctorAlreadyExistException("Doctor Already Exist with Phone Number: " + d.getPhone());
        }

        Doctor doctor = new Doctor();
        doctor.setCreatedAt(LocalDateTime.now());
        doctor.setEmail(d.getEmail());
        doctor.setExperienceYears(d.getExperienceYears());
        doctor.setLicenseNumber(d.getLicenseNumber());
        doctor.setName(d.getName());
        doctor.setSpecialization(d.getSpecialization());
        doctor.setPhone(d.getPhone());

        Doctor saveDoctor = doctorRepository.save(doctor);

        return maptoResponse(saveDoctor);
    }

    public DoctorResponse getDoctorById(Long id){
        Doctor doc = doctorRepository.findById(id).orElseThrow(() -> new DoctorNotFoundException("Doctor does not exist with id: " + id));
        return maptoResponse(doc);
    }

    public List<DoctorResponse> getAllDoctor(){
        List<Doctor> doctors = doctorRepository.findAll();

        List<DoctorResponse> docRes = new ArrayList<>();

        for(Doctor d : doctors){
            docRes.add(maptoResponse(d));
        }
        return docRes;
    }

    @Transactional 
    public DoctorResponse updateDoctor(Long id , DoctorRequest d){
        Doctor existingDoctor = doctorRepository.findById(id).orElseThrow(() -> new DoctorNotFoundException("Doctor does not Exist with id: " + id));

        if(doctorRepository.existsByEmailAndIdNot(d.getEmail(), id)){
            throw new DoctorAlreadyExistException("Doctor Already Exist with Email: " + d.getEmail());
        }

        if(doctorRepository.existsByLicenseNumberAndIdNot(d.getLicenseNumber(), id)){
            throw new DoctorAlreadyExistException("Doctor Already Exist with License Number: " + d.getLicenseNumber());
        }

        if(doctorRepository.existsByPhoneAndIdNot(d.getPhone(), id)){
            throw new DoctorAlreadyExistException("Doctor Already Exist with Phone Number: " + d.getPhone());
        }

        existingDoctor.setName(d.getName());
        existingDoctor.setEmail(d.getEmail());
        existingDoctor.setExperienceYears(d.getExperienceYears());
        existingDoctor.setLicenseNumber(d.getLicenseNumber());
        existingDoctor.setPhone(d.getPhone());
        existingDoctor.setSpecialization(d.getSpecialization());

        Doctor saveDoctor = doctorRepository.save(existingDoctor);

        return maptoResponse(saveDoctor);
    }

    @Transactional 
    public void deleteDoctor(Long id){
        Doctor existingDoctor = doctorRepository.findById(id).orElseThrow(() -> new DoctorNotFoundException("Doctor does not Exist with Id: " + id));
        
        Set<Department> departments = new HashSet<>(existingDoctor.getDepartments());

        for(Department dept : departments){
            existingDoctor.removeDepartment(dept);
        }

        doctorRepository.delete(existingDoctor);
    }

    @Transactional  
    public void assignDepartment(Long id , Long deptId){
        Doctor doctor = doctorRepository.findById(id).orElseThrow(() -> new DoctorNotFoundException("Doctor Not Found With Id: "+ id));

        Department dept = departmentRepository.findById(deptId).orElseThrow(() -> new DepartmentNotFoundException("Department Not Found with Id: "+ deptId));

        if(doctor.getDepartments().contains(dept)){
            throw new DoctorDepartmentAlreadyExistsException("Doctor already assigned to department");
        }

        doctor.getDepartments().add(dept);
        doctorRepository.save(doctor);
    }

    @Transactional 
    public void removeDepartment(Long docId, Long deptId){

        Doctor doctor = doctorRepository.findById(docId).orElseThrow(() -> new DoctorNotFoundException("Doctor Not Found with Id: " + docId));
        Department department = departmentRepository.findById(deptId).orElseThrow(() -> new DepartmentNotFoundException("Department Not Found with Department Id: " + deptId));


        if(doctor.getDepartments().contains(department)){
            doctor.removeDepartment(department);
        }
        else{
            throw new DoctorDepartmentNotFoundException("Doctor is not assigned to this department.");
        }
    }
}

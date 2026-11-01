package com.HospitalManagementSystem.HospitalManagementSystem.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.HospitalManagementSystem.HospitalManagementSystem.dto.DepartmentRequest;
import com.HospitalManagementSystem.HospitalManagementSystem.dto.DepartmentResponse;
import com.HospitalManagementSystem.HospitalManagementSystem.dto.DoctorSummaryResponse;
import com.HospitalManagementSystem.HospitalManagementSystem.entity.Department;
import com.HospitalManagementSystem.HospitalManagementSystem.entity.Doctor;
import com.HospitalManagementSystem.HospitalManagementSystem.exception.DepartmentAlreadyExist;
import com.HospitalManagementSystem.HospitalManagementSystem.exception.DepartmentNotFoundException;
import com.HospitalManagementSystem.HospitalManagementSystem.repository.DepartmentRepository;

import jakarta.transaction.Transactional;

@Service 
public class DepartmentService {
    
    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository){
        this.departmentRepository = departmentRepository;
    }

    private DepartmentResponse mapToResponse(Department d){
        DepartmentResponse dept = new DepartmentResponse();
        dept.setId(d.getId());
        dept.setName(d.getName());
        dept.setDescription(d.getDescription());

        Set<Doctor> docs = d.getDoctors();
        Set<DoctorSummaryResponse> docSummRe = new HashSet<>();

        for(Doctor x : docs){
            DoctorSummaryResponse temp = new DoctorSummaryResponse();
            temp.setId(x.getId());
            temp.setName(x.getName());
            temp.setSpecialization(x.getSpecialization());

            docSummRe.add(temp);
        }

        dept.setDoctors(docSummRe);

        return dept;
    }

    public DepartmentResponse createDepartment(DepartmentRequest dept){

        if(departmentRepository.existsByName(dept.getName())){
            throw new DepartmentAlreadyExist("Department Already exist with Name: " + dept.getName());
        }

        Department department = new Department();
        department.setName(dept.getName());
        department.setDescription(dept.getDescription());
        department.setDoctors(new HashSet<>());
        Department savedDept = departmentRepository.save(department);

        return mapToResponse(savedDept);

    }

    public DepartmentResponse getDepartmentById(Long id){
        Department dept = departmentRepository.findByIdWithDoctors(id).orElseThrow(()-> new DepartmentNotFoundException("Department Not Found with Id: "+ id));

        return mapToResponse(dept);
    }

    public List<DepartmentResponse> getAllDepartments(){
        List<Department> deptList = departmentRepository.findAll();

        List<DepartmentResponse> deptRespList = new ArrayList<>();

        for(Department d :deptList ){
            deptRespList.add(mapToResponse(d));
        }

        return deptRespList;

    }

    public DepartmentResponse updateDepartment(Long id , DepartmentRequest dept){
        Department existingDept = departmentRepository.findById(id).orElseThrow(() -> new DepartmentNotFoundException("Department Not Found with Id:"+ id));

        if(departmentRepository.existsByNameAndIdNot(dept.getName(), id)){
            throw new DepartmentAlreadyExist("Department Already Exist with Name: " + dept.getName());
        }

        existingDept.setName(dept.getName());
        existingDept.setDescription(dept.getDescription());

        Department savedDept = departmentRepository.save(existingDept);

        return mapToResponse(savedDept);
    }

    @Transactional 
    public void deleteDepartment(Long id){
        Department existingDept = departmentRepository.findById(id).orElseThrow(() -> new DepartmentNotFoundException("Department Not Found with Id:"+ id));
        Set<Doctor> doctors = new HashSet<>(existingDept.getDoctors());

        for(Doctor d : doctors){
            d.removeDepartment(existingDept);
        }
        departmentRepository.delete(existingDept);
    }
    
}

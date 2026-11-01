package com.HospitalManagementSystem.HospitalManagementSystem.exception;

public class DoctorDepartmentAlreadyExistsException extends RuntimeException {
    
    public DoctorDepartmentAlreadyExistsException(String msg){
        super(msg);
    }
}

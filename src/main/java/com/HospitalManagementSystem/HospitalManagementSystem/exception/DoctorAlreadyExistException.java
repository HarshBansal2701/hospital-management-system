package com.HospitalManagementSystem.HospitalManagementSystem.exception;

public class DoctorAlreadyExistException extends RuntimeException {
    
    public DoctorAlreadyExistException(String msg){
        super(msg);
    }
}

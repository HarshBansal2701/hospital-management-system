package com.HospitalManagementSystem.HospitalManagementSystem.exception;

public class PatientAlreadyExistException extends RuntimeException {
    
    public PatientAlreadyExistException(String msg){
        super(msg);
    }
}

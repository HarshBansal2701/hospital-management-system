package com.HospitalManagementSystem.HospitalManagementSystem.exception;

public class DepartmentNotFoundException extends RuntimeException{
    
    public DepartmentNotFoundException(String msg){
        super(msg);
    }
}

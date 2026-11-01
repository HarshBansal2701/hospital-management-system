package com.HospitalManagementSystem.HospitalManagementSystem.exception;

public class DepartmentAlreadyExist extends RuntimeException{
    
    public DepartmentAlreadyExist(String msg){
        super(msg);
    }
}

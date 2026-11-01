package com.HospitalManagementSystem.HospitalManagementSystem.exception;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter 
public class ApiErrorResponse {
    
    private Integer status;
    private String message;
    private Map<String,String> errors; 
}

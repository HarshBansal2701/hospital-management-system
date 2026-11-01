package com.HospitalManagementSystem.HospitalManagementSystem.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// import com.HospitalManagementSystem.HospitalManagementSystem.entity.Patient;

@RestControllerAdvice 
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleValidationError(MethodArgumentNotValidException ex){

        Map<String,String> errors = new HashMap<>();

        for(FieldError e : ex.getBindingResult().getFieldErrors()){
            errors.put(e.getField(), e.getDefaultMessage());
        }

        ApiErrorResponse err = new ApiErrorResponse();
        err.setErrors(errors);
        err.setStatus(HttpStatus.BAD_REQUEST.value());
        err.setMessage(ex.getMessage());

        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(PatientNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handlePatientNotFound(PatientNotFoundException ex){
        
        ApiErrorResponse err = new ApiErrorResponse();
        err.setMessage(ex.getMessage());
        err.setStatus(HttpStatus.NOT_FOUND.value());
        err.setErrors(null);

        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PatientAlreadyExistException.class)
    public ResponseEntity<ApiErrorResponse> handlePatientAlreadyExist(PatientAlreadyExistException ex){

        ApiErrorResponse err = new ApiErrorResponse();
        err.setMessage(ex.getMessage());
        err.setErrors(null);
        err.setStatus(HttpStatus.CONFLICT.value());

        return new ResponseEntity<ApiErrorResponse>(err, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(DoctorAlreadyExistException.class)
    public ResponseEntity<ApiErrorResponse> handleDoctorAlreadyExist(DoctorAlreadyExistException ex){

        ApiErrorResponse err = new ApiErrorResponse();
        err.setMessage(ex.getMessage());
        err.setStatus(HttpStatus.CONFLICT.value());
        err.setErrors(null);

        return new ResponseEntity<>(err, HttpStatus.CONFLICT);
    }
    

    @ExceptionHandler(DoctorNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleDoctorNotFoundException(DoctorNotFoundException ex){

        ApiErrorResponse err = new ApiErrorResponse();
        err.setMessage(ex.getMessage());
        err.setErrors(null);
        err.setStatus(HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(err , HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DepartmentAlreadyExist.class)
    public ResponseEntity<ApiErrorResponse> handleDepartmentAlreadyExistException(DepartmentAlreadyExist ex){

        ApiErrorResponse err = new ApiErrorResponse();
        err.setMessage(ex.getMessage());
        err.setErrors(null);
        err.setStatus(HttpStatus.CONFLICT.value());

        return new ResponseEntity<>(err, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(DepartmentNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleDepartmentNotFoundException(DepartmentNotFoundException ex){

        ApiErrorResponse err = new ApiErrorResponse();
        err.setErrors(null);
        err.setMessage(ex.getMessage());
        err.setStatus(HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DoctorDepartmentAlreadyExistsException.class)
    public ResponseEntity<ApiErrorResponse> handleDoctorDepartmentAlreadyExistsException(DoctorDepartmentAlreadyExistsException ex){

        ApiErrorResponse err = new ApiErrorResponse();
        err.setErrors(null);
        err.setMessage(ex.getMessage());
        err.setStatus(HttpStatus.CONFLICT.value());

        return new ResponseEntity<>(err, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(DoctorDepartmentNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleDoctorDepartmentNotFoundException(DoctorDepartmentNotFoundException ex){

        ApiErrorResponse err = new ApiErrorResponse();
        err.setMessage(ex.getMessage());
        err.setStatus(HttpStatus.NOT_FOUND.value());
        err.setErrors(null);

        return new ResponseEntity<>(err , HttpStatus.NOT_FOUND);
    }
}

package com.monohero.StudentMgtApp.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionsHandler {



    @ExceptionHandler(StudentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String,String> userNotFound(StudentNotFoundException exception){
        Map<String,String> error = new HashMap<>();
        error.put("error", exception.getMessage());
        return error;
    }

    @ExceptionHandler(StudentAlreadyExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String,String> userAlreadyExist(StudentAlreadyExistException exception){
        Map<String,String> error = new HashMap<>();
        error.put("error",exception.getMessage());
        return error;
    }
}

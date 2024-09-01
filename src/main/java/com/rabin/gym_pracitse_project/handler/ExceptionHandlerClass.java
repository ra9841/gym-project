package com.rabin.gym_pracitse_project.handler;

import com.rabin.gym_pracitse_project.exception.gymMemberEmailAlreadyExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandlerClass {


    @ExceptionHandler(gymMemberEmailAlreadyExistException.class)
    public ResponseEntity<Map<String, String>> exceptionHandlingIfGymMemberEmailAlreadyExist(gymMemberEmailAlreadyExistException ex){
        Map<String, String> errorMap=new HashMap<>();
        errorMap.put("message", ex.getMessage());
        errorMap.put("status code", HttpStatus.ALREADY_REPORTED.toString());
        return ResponseEntity.ok(errorMap);
    }
}

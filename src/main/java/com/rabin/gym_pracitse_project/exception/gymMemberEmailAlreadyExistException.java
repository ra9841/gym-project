package com.rabin.gym_pracitse_project.exception;

public class gymMemberEmailAlreadyExistException extends RuntimeException{
    public gymMemberEmailAlreadyExistException(String message) {
        super(message);
    }
}

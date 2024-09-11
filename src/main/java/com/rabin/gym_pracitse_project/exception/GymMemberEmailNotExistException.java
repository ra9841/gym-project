package com.rabin.gym_pracitse_project.exception;

public class GymMemberEmailNotExistException extends RuntimeException {
    public GymMemberEmailNotExistException(String message) {
        super(message);
    }
}

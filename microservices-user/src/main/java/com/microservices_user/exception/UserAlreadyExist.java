package com.microservices_user.exception;

public class UserAlreadyExist extends RuntimeException {
    public UserAlreadyExist() {
        super("User already exist");
    }
}

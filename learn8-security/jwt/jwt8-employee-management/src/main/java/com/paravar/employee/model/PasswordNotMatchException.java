package com.paravar.employee.model;

public class PasswordNotMatchException extends RuntimeException {
    public PasswordNotMatchException(String message) {
        super(message);
    }


    public static PasswordNotMatchException of() {
        return new PasswordNotMatchException("Password not matching");
    }
}

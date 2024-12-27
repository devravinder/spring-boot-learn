package com.paravar;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {
        super("User not found with id: " + id);
    }
    public UserNotFoundException(String id) {
        super("User not found with id: " + id);
    }
    public static UserNotFoundException of(Long id){
        return new UserNotFoundException("User not found with id: " + id);
    }
}

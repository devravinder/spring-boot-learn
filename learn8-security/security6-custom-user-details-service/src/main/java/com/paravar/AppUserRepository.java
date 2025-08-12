package com.paravar;

public interface AppUserRepository {

    public AppUser findCustomUserByEmail(String email);

}
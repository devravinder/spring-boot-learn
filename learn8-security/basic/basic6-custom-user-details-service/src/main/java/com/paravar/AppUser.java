package com.paravar;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class AppUser {

    private final long id;
    private final String email;
    @JsonIgnore
    private final String password;
}
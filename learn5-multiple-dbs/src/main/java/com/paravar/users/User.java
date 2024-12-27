package com.paravar.users;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
@Builder
@Table(name = "users") // user is reserved table keyword in h2 database
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String email;
    int age;
}

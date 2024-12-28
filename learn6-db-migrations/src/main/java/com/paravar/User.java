package com.paravar;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_id_generator")
    @SequenceGenerator(name = "users_id_generator", sequenceName = "users_id_seq",initialValue = 1, allocationSize = 20)
    private Long id;
    private String username;
    private String email;
    private String password;
    private Integer age;
}

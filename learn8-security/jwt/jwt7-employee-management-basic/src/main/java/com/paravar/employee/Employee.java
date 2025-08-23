package com.paravar.employee;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.paravar.models.Role;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="employees")
@Setter
@Getter
@ToString(exclude = {"manager"}) // Exclude manager from toString to avoid lazy loading
@EqualsAndHashCode(exclude = {"id", "password", "manager"}) // Exclude manager from equals/hashCode
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    @JsonIgnore // we should use DTO
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;


    @JoinColumn(name = "manager_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Employee manager;

//    private String managerId;




/*    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    private boolean enabled = true;*/

}
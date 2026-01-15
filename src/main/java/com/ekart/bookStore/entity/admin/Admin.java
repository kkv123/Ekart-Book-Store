package com.ekart.bookStore.entity.admin;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="admin_details")
public class Admin {

    @Id
    private String admin_id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true,nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;

    @Column(nullable = false,updatable = false)
    private LocalDateTime created_at=LocalDateTime.now();

}

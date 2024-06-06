package com.amalitechnss.Lizzy_fileServer.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.Length;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private  Long UserId;
    private String firstname;
    private  String lastname;
    @Column(nullable = false, unique = true)
    private  String email;
    @Column(length = 60)
    private String password;
    private  String role;
    private  boolean isEnabled= false;

}

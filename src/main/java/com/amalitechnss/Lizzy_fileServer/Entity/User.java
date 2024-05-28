package com.amalitechnss.Lizzy_fileServer.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private  Long UserId;
    private String Username;
    private LocalDateTime CreatedAt;
    private  String Role;
   // private Set< Document>  Documents;
}

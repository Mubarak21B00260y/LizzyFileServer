package com.amalitechnss.Lizzy_fileServer.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private  Long UserId;
    private String Username;
    private LocalDateTime CreatedAt;
    private  String Role;
    private Set< Document>  Documents;
}

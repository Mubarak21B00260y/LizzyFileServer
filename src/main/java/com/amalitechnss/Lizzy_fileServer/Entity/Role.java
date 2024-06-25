package com.amalitechnss.Lizzy_fileServer.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    @Id
    @GeneratedValue
    private  Long Id;
    @Column(unique = true)
    private  String name;
   @ManyToMany(mappedBy = "roles")
   @JsonIgnore
  private List<User> users;


    public Role(String name) {
        this.name=name;
    }
}

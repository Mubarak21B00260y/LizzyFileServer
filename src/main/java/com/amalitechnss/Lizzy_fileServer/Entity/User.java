package com.amalitechnss.Lizzy_fileServer.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
@NoArgsConstructor
@Getter
@Setter
@Entity

public class User implements UserDetails,Principal{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private  Long userId;
    private String firstname;
    private  String lastname;
    @Column

    private  String email;
    @Column(length = 60,nullable = false)
    private String password;
    private  boolean isEnabled;
    private boolean accountLocked;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return  this.roles.stream().map(role->new SimpleGrantedAuthority( role.getName())).collect(Collectors.toList());
    }



    @Override
    public String getName() {
        return email;
    }



    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !accountLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
    public  String getFullName(){

        return  firstname+""+lastname;
    }
}

///



package com.amalitechnss.Lizzy_fileServer.Repository;

import com.amalitechnss.Lizzy_fileServer.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {

    Optional<Role>  findByName(String name);
}

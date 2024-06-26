package com.amalitechnss.Lizzy_fileServer;

import com.amalitechnss.Lizzy_fileServer.Entity.Role;
import com.amalitechnss.Lizzy_fileServer.Repository.RoleRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.util.Optional;

@SpringBootApplication
public class LizzyFileServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(LizzyFileServerApplication.class, args);
	}



	@Bean
	public CommandLineRunner runner(RoleRepository roleRepository) {
		return args -> {
			if (roleRepository.findByName("USER").isEmpty()) {
				roleRepository.save(new Role("USER"));
			}
		};
	}

	@Bean
	public CommandLineRunner AdminInitializer(RoleRepository roleRepository ) {




		  return  args -> {
			  Optional< Role>  optionalRole= roleRepository.findByName("ADMIN");
			    Role Admin = optionalRole.orElseGet(()->{
					Role newAdminRole= new Role("ADMIN");
					 roleRepository.save(newAdminRole);
					return  newAdminRole;
				});



		  };







	}




}

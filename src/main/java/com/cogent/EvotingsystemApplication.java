package com.cogent;

import com.cogent.backend.domains.Role;
import com.cogent.backend.domains.User;
import com.cogent.services.AdminService;
import static com.cogent.utils.AdminCreation.createAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableJpaRepositories(basePackages = {"com.cogent.backend.repositories"})
@SpringBootApplication()
@EnableTransactionManagement
public class EvotingsystemApplication implements CommandLineRunner {

    @Autowired
    private AdminService service;



    public static void main(String[] args) {
        SpringApplication.run(EvotingsystemApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Role role = new Role(2,"ROLE_ADMIN");
        User admin = createAdmin(role);
        String message = service.createAdmin(admin, role);
        System.out.println(message);
    }
}

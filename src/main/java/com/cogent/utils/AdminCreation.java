package com.cogent.utils;

import com.cogent.backend.domains.Role;
import com.cogent.backend.domains.User;
import com.cogent.backend.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class AdminCreation {

    @Autowired
    private static RoleRepository roleRepository;

    public static User createAdmin(Role role){


          User admin = new User("Shekinah","Shekinah", "Bakupa", "teddy94@gmail.com", "Pittsbrug",
                  "PA","2403449117","Shekinah1994",role);
          admin.setVoteredPresident(true);
          admin.setVoteredMayor(true);
          admin.setImageUrl("https://shek-devops-bucket.s3.us-east-2.amazonaws.com/shekinah.JPG");

          return admin;
    }
}

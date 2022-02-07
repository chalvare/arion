package com.chalvare.zuul.util;


import com.chalvare.zuul.security.entity.Role;
import com.chalvare.zuul.security.enums.RoleName;
import com.chalvare.zuul.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
/*
@Component
public class CreateRoles implements CommandLineRunner {

    @Autowired
    RoleService rolService;

    @Override
    public void run(String... args) throws Exception {
        Role rolAdmin = new Role(RoleName.ROLE_ADMIN);
        Role rolUser = new Role(RoleName.ROLE_USER);
        rolService.save(rolAdmin);
        rolService.save(rolUser);
    }
}
*/


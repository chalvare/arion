package com.chalvare.zuul.service;

import com.chalvare.zuul.repository.RoleRepository;
import com.chalvare.zuul.security.entity.Role;
import com.chalvare.zuul.security.enums.RoleName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    public Optional<Role> getByRoleNombre(RoleName roleName){
        return  roleRepository.findByRoleName(roleName);
    }

    public void save(Role role){
        roleRepository.save(role);
    }
}

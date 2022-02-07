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

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Optional<Role> getByRoleName(RoleName roleName){
        return  roleRepository.findByRoleName(roleName);
    }

    public void save(Role role){
        roleRepository.save(role);
    }
}

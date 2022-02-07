package com.chalvare.zuul.controller;

import com.chalvare.zuul.dto.JwtDto;
import com.chalvare.zuul.dto.LoginUser;
import com.chalvare.zuul.dto.CustomerDto;
import com.chalvare.zuul.jwt.JwtProvider;
import com.chalvare.zuul.security.entity.Customer;
import com.chalvare.zuul.security.entity.Role;
import com.chalvare.zuul.security.enums.RoleName;
import com.chalvare.zuul.service.RoleService;
import com.chalvare.zuul.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    CustomerService customerService;

    @Autowired
    RoleService roleService;

    @Autowired
    JwtProvider jwtProvider;

    //Espera un json y lo convierte a tipo clase NuevoUsuario
    @PostMapping("/nuevoUsuario")
    public ResponseEntity<?> nuevoUsuario(@Valid @RequestBody CustomerDto customerDto,
                                          BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity<>("Campos mal o email invalido", HttpStatus.BAD_REQUEST);
        }
        if(customerService.existsByCustomer(customerDto.getNameCustomer())){
            return new ResponseEntity<>("Ese nombre ya existe", HttpStatus.BAD_REQUEST);
        }
        if(customerService.existsByEmail(customerDto.getEmail())){
            return new ResponseEntity<>("Ese email ya existe", HttpStatus.BAD_REQUEST);
        }

        Customer customer = new Customer(customerDto.getName(), customerDto.getNameCustomer(),
                customerDto.getEmail(), passwordEncoder.encode(customerDto.getPassword()));

        Set<Role> roles = new HashSet<>();
        roles.add(roleService.getByRoleNombre(RoleName.ROLE_USER).get());
        if(customerDto.getRoles().contains("admin"))
            roles.add(roleService.getByRoleNombre(RoleName.ROLE_ADMIN).get());
        customer.setRoles(roles);

        customerService.save(customer);

        return new ResponseEntity<>("Usuario creado", HttpStatus.CREATED);
    }

    @PostMapping("/login")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUser loginUser, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return new ResponseEntity("Campos mal", HttpStatus.BAD_REQUEST);
        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(loginUser.getNombreUsuario(),
                                loginUser.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        return new ResponseEntity<>(jwtDto, HttpStatus.OK);
    }
}

package com.chalvare.zuul.controller;

import com.chalvare.zuul.dto.CustomerDto;
import com.chalvare.zuul.dto.JwtDto;
import com.chalvare.zuul.dto.LoginUser;
import com.chalvare.zuul.jwt.JwtProvider;
import com.chalvare.zuul.security.entity.Customer;
import com.chalvare.zuul.security.entity.Role;
import com.chalvare.zuul.security.enums.RoleName;
import com.chalvare.zuul.service.CustomerService;
import com.chalvare.zuul.service.RoleService;
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
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    public static final String ADMIN = "admin";
    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final CustomerService customerService;

    private final RoleService roleService;

    private final JwtProvider jwtProvider;

    @Autowired
    public AuthController(PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, CustomerService customerService, RoleService roleService, JwtProvider jwtProvider) {
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.customerService = customerService;
        this.roleService = roleService;
        this.jwtProvider = jwtProvider;
    }

    //Espera un json y lo convierte a tipo clase NuevoUsuario
    @PostMapping("/signup")
    public ResponseEntity<String> nuevoUsuario(@Valid @RequestBody CustomerDto customerDto,
                                          BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity<>("Campos mal o email invalido", HttpStatus.BAD_REQUEST);
        }
        if(Boolean.TRUE.equals(customerService.existsByCustomer(customerDto.getNameCustomer()))){
            return new ResponseEntity<>("Ese nombre ya existe", HttpStatus.BAD_REQUEST);
        }
        if(Boolean.TRUE.equals(customerService.existsByEmail(customerDto.getEmail()))){
            return new ResponseEntity<>("Ese email ya existe", HttpStatus.BAD_REQUEST);
        }

        Customer customer = new Customer(customerDto.getName(), customerDto.getNameCustomer(),
                customerDto.getEmail(), passwordEncoder.encode(customerDto.getPassword()));

        Set<Role> roles = new HashSet<>();
        roleService.getByRoleName(RoleName.ROLE_USER).ifPresent(roles::add);
        if(customerDto.getRoles().contains(ADMIN))
            roleService.getByRoleName(RoleName.ROLE_ADMIN).ifPresent(roles::add);
        customer.setRoles(roles);

        customerService.save(customer);

        return new ResponseEntity<>("customer created", HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUser loginUser, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return new ResponseEntity<>(new JwtDto("Empty Token", loginUser.getNameCustomer(), Collections.emptyList()), HttpStatus.BAD_REQUEST);
        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(loginUser.getNameCustomer(),
                                loginUser.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        return new ResponseEntity<>(jwtDto, HttpStatus.OK);
    }

    @GetMapping(value = "/hello")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String helloWorld() {
        return "Hello World111! " + new Date();
    }

    @GetMapping(value = "/hello2")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String helloWorld2() {
        return "Hello World2222! " + new Date();
    }
}

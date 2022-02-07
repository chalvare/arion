package com.chalvare.zuul.security.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Customer {
    @Id
    //Id Auto Increment
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCustomer;
    //Decorador para indicar que no puede ser null el campo
    @NotNull
    private String name;
    @NotNull
    @Column(unique = true)
    private String nameCustomer;
    @NotNull
    @Column(unique = true)
    private String email;
    @NotNull
    private String password;

    @NotNull
    //Relación many to many
    //Un usuario puede tener MUCHOS roles y un rol puede PERTENECER a varios usuarios
    //Tabla intermedia que tiene dos campos que va a tener idUsuario y idRol
    @ManyToMany
    // join columns hace referencia a la columna que hace referencia hacia esta
    // Es decir la tabla usuario_rol va a tener un campo que se llama id_usuario
    // inverseJoinColumns = el inverso, hace referencia a rol
    @JoinTable(name = "customer_role", joinColumns = @JoinColumn(name = "id_customer"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    //Constuctor sin Id ni Roles
    public Customer(@NotNull String name,
                    @NotNull String nameCustomer,
                    @NotNull String email,
                    @NotNull String password) {
        this.name = name;
        this.nameCustomer = nameCustomer;
        this.email = email;
        this.password = password;
    }

    public Customer(){

    }
}

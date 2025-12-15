package com.usco.pruebatecnica.authentication.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String nombre;
    private String email;
    private String password;
    private Role rol;

    public User(String name, String email, String password, Role rol) {

        this(null, name, email, password, rol);
    }
}

package com.usco.pruebatecnica.authentication.domain;

import java.util.Optional;

public interface UserRepository {
    User save(User usuario);

    Optional<User> findByEmail(String email);
}

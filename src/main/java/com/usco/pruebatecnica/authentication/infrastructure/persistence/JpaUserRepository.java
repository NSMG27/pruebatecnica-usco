package com.usco.pruebatecnica.authentication.infrastructure.persistence;

import com.usco.pruebatecnica.authentication.domain.User;
import com.usco.pruebatecnica.authentication.domain.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class JpaUserRepository implements UserRepository {
    private final SpringDataUserRepository repository;

    public JpaUserRepository(SpringDataUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User save(User User) {
        UserEntity entity = UserMapper.toEntity(User);
        return UserMapper.toDomain(repository.save(entity));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return repository.findByEmail(email).map(UserMapper::toDomain);
    }
}

package ru.itis.jlab.repositories;

import ru.itis.jlab.model.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<Long, User> {
    Optional<User> findByConfirmCode(String code);

    Optional<User> findByLogin(String login);

    Optional<User> findByEmail(String email);

    void deleteById(Long userId);
}

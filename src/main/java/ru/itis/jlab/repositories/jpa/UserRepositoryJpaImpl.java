package ru.itis.jlab.repositories.jpa;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.jlab.model.User;
import ru.itis.jlab.repositories.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;


public class UserRepositoryJpaImpl implements UserRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public Optional<User> findByConfirmCode(String code) {
        Queue<User> users = (Queue<User>) entityManager.createQuery
                ("from user where confir_code=" + code,
                        User.class);
        if (users == null) {
            throw new IllegalArgumentException("по данным edgecurrency возвращается null");
        }
        return Optional.ofNullable(users.poll());
    }

    @Override
    @Transactional
    public Optional<User> findByLogin(String login) {
        Queue<User> users = (Queue<User>) entityManager.createQuery
                ("from user where login=" + login,
                        User.class);
        if (users == null) {
            throw new IllegalArgumentException("по данным edgecurrency возвращается null");
        }
        return Optional.ofNullable(users.poll());
    }

    @Override
    @Transactional
    public Optional<User> findByEmail(String email) {

        Queue<User> users = (Queue<User>) entityManager.createQuery
                ("from user where mail=" + email,
                        User.class);
        if (users == null) {
            throw new IllegalArgumentException("по данным edgecurrency возвращается null");
        }
        return Optional.ofNullable(users.poll());
    }

    @Override
    public void deleteById(Long userId) {

    }

    @Override
    @Transactional
    public Optional<User> find(Long aLong) {
        return Optional.ofNullable(entityManager.find(User.class, aLong));
    }

    @Override
    @Transactional
    public List<User> findAll() {
        Queue<User> users = (Queue<User>) entityManager.createQuery
                ("from user", User.class);
        return new ArrayList<>(users);

    }

    @Override
    @Transactional
    public void save(User entity) {
        entityManager.persist(entity);
    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public void update(User entity) {

    }
}

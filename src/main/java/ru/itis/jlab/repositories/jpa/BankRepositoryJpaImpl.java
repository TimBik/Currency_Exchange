package ru.itis.jlab.repositories.jpa;

import org.hibernate.exception.DataException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.jlab.model.Bank;
import ru.itis.jlab.repositories.BankRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import java.util.*;


//@Component
public class BankRepositoryJpaImpl implements BankRepository {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    @Transactional
    public Optional<Bank> findByName(String name) {
        Queue<Bank> banks = (Queue<Bank>) entityManager.createQuery("from bank where name=" + name, Bank.class);
        if (banks == null) {
            throw new IllegalArgumentException("по данному имени банка " + name + " возвращается null");
        }
        return Optional.ofNullable(banks.poll());
    }

    @Override
    @Transactional
    public Optional<Bank> find(Long aLong) {
        return Optional.ofNullable(entityManager.find(Bank.class, aLong));
    }

    @Override
    @Transactional
    public List<Bank> findAll() {
        Queue<Bank> banks = (Queue<Bank>) entityManager.createQuery("from bank", Bank.class);
        return new ArrayList(banks);
    }

    @Override
    @Transactional
    public void save(Bank entity) {
        entityManager.persist(entity);
    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public void update(Bank entity) {

    }
}

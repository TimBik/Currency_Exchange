package ru.itis.jlab.repositories.jpa;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.jlab.model.Currency;
import ru.itis.jlab.repositories.CurrencyRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;


public class CurrencyRepositoryJpaImpl implements CurrencyRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public Optional<Currency> findByName(String nameCurrency) {
        Queue<Currency> currencies = (Queue<Currency>) entityManager.createQuery("from currency where name=" + nameCurrency, Currency.class);
        if (currencies == null) {
            throw new IllegalArgumentException("по данному имени банка " + nameCurrency + " возвращается null");
        }
        return Optional.ofNullable(currencies.poll());
    }

    @Override
    @Transactional
    public Optional<Currency> find(Long aLong) {
        return Optional.ofNullable(entityManager.find(Currency.class, aLong));
    }

    @Override
    @Transactional
    public List<Currency> findAll() {
        Queue<Currency> currencies = (Queue<Currency>) entityManager.createQuery("from currency", Currency.class);
        return new ArrayList(currencies);
    }

    @Override
    @Transactional
    public void save(Currency entity) {
        entityManager.persist(entity);
    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public void update(Currency entity) {

    }
}

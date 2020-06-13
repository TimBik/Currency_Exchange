package ru.itis.jlab.repositories.jpa;

import com.sun.javafx.geom.Edge;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.jlab.model.EdgeCurrency;
import ru.itis.jlab.repositories.EdgeCurrencyRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;


public class EdgeCurrencyRepositoryJpaImpl implements EdgeCurrencyRepository {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public Optional<EdgeCurrency> findByBankIdAndCurrencyNames(long bankId, long idFromCurrency, long idToCurrency) {
        Queue<EdgeCurrency> currencies = (Queue<EdgeCurrency>) entityManager.createQuery
                ("from edge_currency where bank_id=" + bankId
                                + "and currency_from_id=" + idFromCurrency
                                + "and currency_to_id=" + idToCurrency,
                        EdgeCurrency.class);
        if (currencies == null) {
            throw new IllegalArgumentException("по данным edgecurrency возвращается null");
        }
        return Optional.ofNullable(currencies.poll());
    }

    @Override
    @Transactional
    public List<EdgeCurrency> findAllByBankId(long bankId) {
        Queue<EdgeCurrency> edgeCurrencies = (Queue<EdgeCurrency>) entityManager.createQuery
                ("from edge_currency where bank_id=" + bankId);
        return new ArrayList<>(edgeCurrencies);
    }

    @Override
    @Transactional
    public List<EdgeCurrency> findByCurrenciesId(long idCurrencyFrom, long idCurrencyTo) {
        Queue<EdgeCurrency> edgeCurrencies = (Queue<EdgeCurrency>) entityManager.createQuery
                ("from edge_currency where currency_from_id=" + idCurrencyFrom +
                        "and currency_to_id=" + idCurrencyTo);
        return new ArrayList<>(edgeCurrencies);
    }

    @Override
    @Transactional
    public Optional<EdgeCurrency> find(Long aLong) {
        return Optional.ofNullable(entityManager.find(EdgeCurrency.class, aLong));
    }

    @Override
    @Transactional
    public List<EdgeCurrency> findAll() {
        Queue<EdgeCurrency> edgeCurrencies = (Queue<EdgeCurrency>) entityManager.createQuery
                ("from edge_currency", EdgeCurrency.class);
        return new ArrayList<>(edgeCurrencies);
    }

    @Override
    @Transactional
    public void save(EdgeCurrency entity) {
        entityManager.persist(entity);
    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public void update(EdgeCurrency entity) {

    }
}

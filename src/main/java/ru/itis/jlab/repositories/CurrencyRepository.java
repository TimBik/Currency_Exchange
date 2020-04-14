package ru.itis.jlab.repositories;

import ru.itis.jlab.model.Currency;

import java.util.Optional;

public interface CurrencyRepository extends CrudRepository<Long, Currency> {

    Optional<Currency> findByName(String nameCurrency);
}

package ru.itis.jlab.repositories;

import ru.itis.jlab.model.EdgeCurrency;

import java.util.List;
import java.util.Optional;

public interface EdgeCurrencyRepository extends CrudRepository<Long, EdgeCurrency> {
    Optional<EdgeCurrency> findByBankIdAndCurrencyNames(long bankId, long idFromCurrency, long idToCurrency);

    List<EdgeCurrency> findAllByBankId(long bankId);

    List<EdgeCurrency> findByCurrenciesId(long idCurrencyFrom, long idCurrencyTo);
}

package ru.itis.jlab.services.modelServices;

import ru.itis.jlab.model.Currency;

import java.util.List;
import java.util.Optional;

public interface CurrencyService {

    Optional<Currency> findByCurrencyName(String nameCurrency);

    void save(Currency currency);

    Optional<Currency> find(long idCurrencyFrom);

    List<Currency> findAll();
}

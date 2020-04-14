package ru.itis.jlab.services.modelServices;

import java.util.Optional;

import org.springframework.stereotype.Service;
import ru.itis.jlab.model.Currency;
import ru.itis.jlab.model.EdgeCurrency;

public interface EdgeCurrencyService {
    Optional<EdgeCurrency> getCurrencyById(long id);


    void save(EdgeCurrency edgeCurrency);
}

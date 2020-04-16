package ru.itis.jlab.services.modelServices;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import ru.itis.jlab.model.Currency;
import ru.itis.jlab.model.EdgeCurrency;

public interface EdgeCurrencyService {
    Optional<EdgeCurrency> getCurrencyById(long id);



    void save(EdgeCurrency edgeCurrency);

    List<EdgeCurrency> findAllByBankId(Long id);

    List<EdgeCurrency> findByCurrenciesId(long id, long id1);
}

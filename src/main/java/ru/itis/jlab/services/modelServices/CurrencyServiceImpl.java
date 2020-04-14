package ru.itis.jlab.services.modelServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.jlab.model.Currency;
import ru.itis.jlab.repositories.CurrencyRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CurrencyServiceImpl implements CurrencyService {
    @Autowired
    CurrencyRepository currencyRepository;

    @Override
    public Optional<Currency> findByCurrencyName(String nameCurrency) {
        return currencyRepository.findByName(nameCurrency);
    }

    @Override
    public void save(Currency currency) {
        currencyRepository.save(currency);
    }

    @Override
    public Optional<Currency> find(long idCurrencyFrom) {
        return currencyRepository.find(idCurrencyFrom);
    }

    @Override
    public List<Currency> findAll() {
        return currencyRepository.findAll();
    }
}

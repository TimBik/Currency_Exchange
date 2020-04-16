package ru.itis.jlab.services.matrix;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.jlab.model.Currency;
import ru.itis.jlab.model.EdgeCurrency;
import ru.itis.jlab.repositories.EdgeCurrencyRepository;
import ru.itis.jlab.services.modelServices.EdgeCurrencyService;

import java.util.List;
import java.util.Optional;

@Service
public class BestEdgeCurrenciesService {
    @Autowired
    FindMinLogCostService findMinCostService;
    @Autowired
    EdgeCurrencyService edgeCurrencyService;

    public Optional<EdgeCurrency> createBestEdgeCurrencyByCurrencies(Currency currency1, Currency currency2) {
        List<EdgeCurrency> edgeCurrencies = edgeCurrencyService.findByCurrenciesId(currency1.getId(), currency2.getId());
        EdgeCurrency edgeCurrency = findMinCostService.findMinCostByOne(edgeCurrencies);
        return Optional.ofNullable(edgeCurrency);
    }

}

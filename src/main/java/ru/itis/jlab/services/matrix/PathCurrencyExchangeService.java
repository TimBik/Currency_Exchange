package ru.itis.jlab.services.matrix;

import ru.itis.jlab.dto.EdgeCurrencyWithNamesDto;
import ru.itis.jlab.model.Currency;

import java.util.List;

public interface PathCurrencyExchangeService {
    List<EdgeCurrencyWithNamesDto> findBestPath(Currency startCurrency, Currency endCurrency);

}

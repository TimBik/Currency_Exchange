package ru.itis.jlab.services;

import ru.itis.jlab.dto.EdgeCurrencyWithNamesDto;
import ru.itis.jlab.model.EdgeCurrency;

public interface Converter {
    EdgeCurrencyWithNamesDto convertFromEdgeCurrencyToEdgeCurrencyWithNamesDto(EdgeCurrency edgeCurrency);

}

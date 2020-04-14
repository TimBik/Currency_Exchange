package ru.itis.jlab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.jlab.dto.EdgeCurrencyWithNamesDto;
import ru.itis.jlab.model.Bank;
import ru.itis.jlab.model.Currency;
import ru.itis.jlab.model.EdgeCurrency;
import ru.itis.jlab.services.modelServices.BankService;
import ru.itis.jlab.services.modelServices.CurrencyService;

import java.util.Optional;

@Service
public class ConverterImpl implements Converter {
    @Override
    public EdgeCurrencyWithNamesDto convertFromEdgeCurrencyToEdgeCurrencyWithNamesDto(EdgeCurrency edgeCurrency) {
        EdgeCurrencyWithNamesDto edgeCurrencyWithNamesDto = new EdgeCurrencyWithNamesDto();
        edgeCurrencyWithNamesDto.setId(edgeCurrency.getId());
        if (edgeCurrency.getBank().getId() > 0) {
            Optional<Bank> optionalBank = Optional.ofNullable(edgeCurrency.getBank());
            if (optionalBank.isPresent()) {
                edgeCurrencyWithNamesDto.setBankName(optionalBank.get().getName());
            }
        } else if (edgeCurrency.getBank().getId() == -1) {
            edgeCurrencyWithNamesDto.setBankName("любое отделение банков");
        }
        if (edgeCurrency.getCurrencyTo().getId() > 0 && edgeCurrency.getCurrencyFrom().getId() > 0) {
            Optional<Currency> currencyFrom = Optional.ofNullable(edgeCurrency.getCurrencyFrom());
            Optional<Currency> currencyTo = Optional.ofNullable(edgeCurrency.getCurrencyTo());
            if (currencyFrom.isPresent() && currencyTo.isPresent()) {
                edgeCurrencyWithNamesDto.setNameCurrencyFrom(currencyFrom.get().getName());
                edgeCurrencyWithNamesDto.setNameCurrencyTo(currencyTo.get().getName());
            }
        }
        edgeCurrencyWithNamesDto.setUrlFromData(edgeCurrency.getUrlFromData());
        edgeCurrencyWithNamesDto.setParsingXPath(edgeCurrency.getParsingXPath());
        edgeCurrencyWithNamesDto.setCostByOne(edgeCurrency.getCostByOne());
        return edgeCurrencyWithNamesDto;
    }
}

package ru.itis.jlab.services.modelServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.jlab.model.EdgeCurrency;
import ru.itis.jlab.repositories.EdgeCurrencyRepository;
import ru.itis.jlab.services.matrix.MatrixService;
import ru.itis.jlab.services.banks_site_parsing.ParsingCurrencyService;

import java.util.Optional;

@Service
public class EdgeCurrencyServiceImpl implements EdgeCurrencyService {
    @Autowired
    EdgeCurrencyRepository edgeCurrencyRepository;

    @Override
    public Optional<EdgeCurrency> getCurrencyById(long id) {
        return edgeCurrencyRepository.find(id);
    }

    @Autowired
    ParsingCurrencyService parsingCurrencyService;

    @Autowired
    MatrixService matrixService;

    @Override
    public void save(EdgeCurrency edgeCurrency) {
        Optional<Double> optionalCostByOne = parsingCurrencyService.findCurrencyCostByOneByEdgeCurrency(edgeCurrency);
        if (optionalCostByOne.isPresent()) {
            Double cost = optionalCostByOne.get();
            edgeCurrency.setCostByOne(cost);
            edgeCurrency.setLogCostByOne(-Math.log(cost));
            matrixService.updateMatrix(edgeCurrency, cost);
            edgeCurrencyRepository.save(edgeCurrency);
        } else {
            throw new IllegalArgumentException("Невозможно правивльно узнать новую стоимость валюты. Проверьте prefix, suffix и url новой валюты по url - " + edgeCurrency.getUrlFromData());
        }
    }


}

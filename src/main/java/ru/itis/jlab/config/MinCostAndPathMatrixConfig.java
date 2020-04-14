package ru.itis.jlab.config;

import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.itis.jlab.model.Currency;
import ru.itis.jlab.model.EdgeCurrency;
import ru.itis.jlab.repositories.CurrencyRepository;
import ru.itis.jlab.services.matrix.MinCostAndPathTablesService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@PropertySource("classpath:properties/application.properties")
@ComponentScan(basePackages = "ru.itis.jlab")
public class MinCostAndPathMatrixConfig {
    @Autowired
    @Qualifier("bestCurrencyTable")
    private Map<Pair<Currency, Currency>, EdgeCurrency> bestCurrencyTable;


    @Autowired
    CurrencyRepository currencyRepository;

    @Autowired
    MinCostAndPathTablesService minCostAndPathTablesService;


    @Bean(name = "minCostAndPathTables")
    public Pair
            <Map<Pair<Currency, Currency>, EdgeCurrency>,
                    Map<Pair<Currency, Currency>, Currency>>
    getMinCostAndPathTablesFloydsAlgorithm() {
        Map<Pair<Currency, Currency>, EdgeCurrency> minCostTable = new HashMap<>();
        Map<Pair<Currency, Currency>, Currency> pathMinCostTable = new HashMap<>();
        for (Pair<Currency, Currency> key : bestCurrencyTable.keySet()) {
            minCostTable.put(key, bestCurrencyTable.get(key));
            pathMinCostTable.put(key, key.getKey());
        }
        List<Currency> currencyList = currencyRepository.findAll();
        for (Currency currency : currencyList) {
            minCostAndPathTablesService.updateMinCostAndPathTablesWithUsedK(currency, minCostTable, pathMinCostTable);
        }
        return new Pair<>(minCostTable, pathMinCostTable);
    }
}

package ru.itis.jlab.services.matrix;

import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.itis.jlab.model.Currency;
import ru.itis.jlab.model.EdgeCurrency;
import ru.itis.jlab.services.modelServices.CurrencyService;

import java.util.Map;
import java.util.Optional;

@Service
public class MatrixServiceImpl implements MatrixService {

    @Autowired
    @Qualifier("minLogCostAndPathTables")
    Pair<Map<Pair<Currency, Currency>, EdgeCurrency>, Map<Pair<Currency, Currency>, Currency>> minCostAndPathTables;

    @Autowired
    @Qualifier("bestCurrencyTable")
    Map<Pair<Currency, Currency>, EdgeCurrency> bestCurrencyTable;

    @Autowired
    MinLogCostAndPathTablesService minCostAndPathTablesService;

    @Autowired
    CurrencyService currencyService;

    @Override
    public void updateMatrix(EdgeCurrency edgeCurrency, Double newCost) {
        Optional<Currency> currencyFrom = Optional.ofNullable(edgeCurrency.getCurrencyFrom());
        Optional<Currency> currencyTo = Optional.ofNullable(edgeCurrency.getCurrencyTo());
        if (currencyFrom.isPresent() && currencyTo.isPresent()) {
            Pair<Currency, Currency> key = new Pair(currencyFrom.get(), currencyTo.get());
            EdgeCurrency edgeCurrency1 = bestCurrencyTable.get(key);
            if (edgeCurrency1 ==null || newCost < edgeCurrency1.getLogCostByOne()) {
                bestCurrencyTable.put(key, edgeCurrency);

                //работает за O(n^2), где n - кол-во валют
                //в принципе не должно вызывать проблем
                //TO Do: постараться ускоирить
                Map<Pair<Currency, Currency>, EdgeCurrency> minCostTable = minCostAndPathTables.getKey();
                Map<Pair<Currency, Currency>, Currency> pathMinCostTable = minCostAndPathTables.getValue();

                //не уверен что нужны по двум вершинам проходиться
                //наверняка надо только по одной
                //TO DO: подумать какую оставить
                Currency currency1 = key.getKey();
                minCostAndPathTablesService.updateMinCostAndPathTablesWithUsedK(currency1, minCostTable, pathMinCostTable);
                Currency currency2 = key.getValue();
                minCostAndPathTablesService.updateMinCostAndPathTablesWithUsedK(currency2, minCostTable, pathMinCostTable);

            }
        }
    }

}

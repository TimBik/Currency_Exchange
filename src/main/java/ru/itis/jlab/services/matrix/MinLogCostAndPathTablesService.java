package ru.itis.jlab.services.matrix;

import javafx.util.Pair;
import ru.itis.jlab.model.Currency;
import ru.itis.jlab.model.EdgeCurrency;

import java.util.Map;

public interface MinLogCostAndPathTablesService {
    void updateMinCostAndPathTablesWithUsedK(Currency currency,
                                             Map<Pair<Currency, Currency>, EdgeCurrency> minCostTable,
                                             Map<Pair<Currency, Currency>, Currency> pathMinCostTable);
}

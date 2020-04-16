package ru.itis.jlab.services.matrix;

import javafx.util.Pair;
import org.springframework.stereotype.Service;
import ru.itis.jlab.model.Currency;
import ru.itis.jlab.model.EdgeCurrency;

import java.util.Map;

@Service
public class MinLogCostAndPathTablesServiceImpl implements MinLogCostAndPathTablesService {
    @Override
    public void updateMinCostAndPathTablesWithUsedK(Currency currency,
                                                    Map<Pair<Currency, Currency>, EdgeCurrency> minLogCostTable,
                                                    Map<Pair<Currency, Currency>, Currency> pathMinCostTable) {
        double eps = 1e-8;
        for (Pair<Currency, Currency> pair : minLogCostTable.keySet()) {
            double cost1 = minLogCostTable.get(new Pair<>(pair.getKey(), currency)).getLogCostByOne();
            double cost2 = minLogCostTable.get(new Pair<>(currency, pair.getValue())).getLogCostByOne();
            if (cost1 != Double.MIN_VALUE && cost2 != Double.MIN_VALUE) {
                double newCostByOne = cost1 + cost2;
                double nowCostByOne = minLogCostTable.get(pair).getLogCostByOne();
                if (nowCostByOne == Double.MIN_VALUE || (newCostByOne < nowCostByOne && Math.abs(newCostByOne - nowCostByOne) > eps)) {
                    minLogCostTable.get(pair).setLogCostByOne(newCostByOne);
                    pathMinCostTable.put(pair, currency);
                }
            }
        }
    }
}

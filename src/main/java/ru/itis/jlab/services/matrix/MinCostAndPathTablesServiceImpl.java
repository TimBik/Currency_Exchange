package ru.itis.jlab.services.matrix;

import javafx.util.Pair;
import org.springframework.stereotype.Service;
import ru.itis.jlab.model.Currency;
import ru.itis.jlab.model.EdgeCurrency;

import java.util.Map;

@Service
public class MinCostAndPathTablesServiceImpl implements MinCostAndPathTablesService {
    @Override
    public void updateMinCostAndPathTablesWithUsedK(Currency currency,
                                                    Map<Pair<Currency, Currency>, EdgeCurrency> minCostTable,
                                                    Map<Pair<Currency, Currency>, Currency> pathMinCostTable) {
        double eps = 1e-6;
        for (Pair<Currency, Currency> pair : minCostTable.keySet()) {
            double cost1 = minCostTable.get(new Pair<>(pair.getKey(), currency)).getCostByOne();
            double cost2 = minCostTable.get(new Pair<>(currency, pair.getValue())).getCostByOne();
            if (cost1 > 0 && cost2 > 0) {
                double newCostByOne = cost1 * cost2;
                double nowCostByOne = minCostTable.get(pair).getCostByOne();
                if ((newCostByOne < nowCostByOne && Math.abs(newCostByOne - nowCostByOne) > eps) || nowCostByOne < 0) {
                    minCostTable.get(pair).setCostByOne(newCostByOne);
                    pathMinCostTable.put(pair, currency);
                }
            }
        }
    }
}

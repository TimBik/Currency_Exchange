package ru.itis.jlab.services.matrix;

import org.springframework.stereotype.Service;
import ru.itis.jlab.model.EdgeCurrency;

import java.util.List;
@Service
public class FindMinCostService {
    public EdgeCurrency findMinCostByOne(List<EdgeCurrency> edgeCurrencies) {
        //TO DO: как то обработать пустой лист
        //замечание: такое возможно если
        // нет обмена данных валют => не исключение
        // TO DO: придумать логику
        if (edgeCurrencies != null && edgeCurrencies.size() > 0) {
            EdgeCurrency edgeCurrency = edgeCurrencies.get(0);
            for (EdgeCurrency ec :
                    edgeCurrencies) {
                if (ec.getCostByOne() < edgeCurrency.getCostByOne()) {
                    edgeCurrency = ec;
                }
            }
            return edgeCurrency;
        }
        return null;

    }
}

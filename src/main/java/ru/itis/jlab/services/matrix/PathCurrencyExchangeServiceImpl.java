package ru.itis.jlab.services.matrix;

import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.itis.jlab.dto.EdgeCurrencyWithNamesDto;
import ru.itis.jlab.model.Currency;
import ru.itis.jlab.model.EdgeCurrency;
import ru.itis.jlab.services.Converter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class PathCurrencyExchangeServiceImpl implements PathCurrencyExchangeService {

    @Autowired
    @Qualifier("minCostAndPathTables")
    Pair<Map<Pair<Currency, Currency>, EdgeCurrency>, Map<Pair<Currency, Currency>, Currency>> minCostAndPathTables;

    @Autowired
    @Qualifier("bestCurrencyTable")
    Map<Pair<Currency, Currency>, EdgeCurrency> bestCurrencyTable;

    @Autowired
    Converter converter;

    @Override
    public List<EdgeCurrencyWithNamesDto> findBestPath(Currency startCurrency, Currency endCurrency) {
        List<EdgeCurrencyWithNamesDto> ansPath = new ArrayList<>();
        Map<Pair<Currency, Currency>, Currency> allPath = minCostAndPathTables.getValue();
        Currency midCurrency = allPath.get(new Pair<>(startCurrency, endCurrency));
        while (!midCurrency.equalsId(startCurrency)) {
            EdgeCurrency edgeCurrency = bestCurrencyTable.get(new Pair<>(midCurrency, endCurrency));
            EdgeCurrencyWithNamesDto edgeCurrencyWithNamesDto = converter.convertFromEdgeCurrencyToEdgeCurrencyWithNamesDto(edgeCurrency);
            ansPath.add(edgeCurrencyWithNamesDto);
            endCurrency = midCurrency;
            midCurrency = allPath.get(new Pair<>(startCurrency, midCurrency));
        }
        EdgeCurrency edgeCurrency = bestCurrencyTable.get(new Pair<>(midCurrency, endCurrency));
        EdgeCurrencyWithNamesDto edgeCurrencyWithNamesDto = converter.convertFromEdgeCurrencyToEdgeCurrencyWithNamesDto(edgeCurrency);
        if (edgeCurrency.getBank().getId() > 0)
            ansPath.add(edgeCurrencyWithNamesDto);
        return ansPath;
    }

}

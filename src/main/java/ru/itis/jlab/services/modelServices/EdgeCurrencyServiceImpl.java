package ru.itis.jlab.services.modelServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.jlab.model.EdgeCurrency;
import ru.itis.jlab.repositories.EdgeCurrencyRepository;
import ru.itis.jlab.services.banks_site_parsing.FindCurrencyCostService;
import ru.itis.jlab.services.banks_site_parsing.ParsingCurrencyService;
import ru.itis.jlab.services.matrix.MatrixService;
import ru.itis.jlab.services.banks_site_parsing.ParsingCurrencyServiceImpl;

import java.util.List;
import java.util.Optional;

@Service
public class EdgeCurrencyServiceImpl implements EdgeCurrencyService {
    @Autowired
    EdgeCurrencyRepository edgeCurrencyRepository;

    @Override
    public Optional<EdgeCurrency> getCurrencyById(long id) {
        return edgeCurrencyRepository.find(id);
    }


    @Override
    public void save(EdgeCurrency edgeCurrency) {
        edgeCurrencyRepository.save(edgeCurrency);
    }

    @Override
    public List<EdgeCurrency> findAllByBankId(Long id) {
        return edgeCurrencyRepository.findAllByBankId(id);
    }

    @Override
    public List<EdgeCurrency> findByCurrenciesId(long id, long id1) {
        return edgeCurrencyRepository.findByCurrenciesId(id, id1);
    }

}

package ru.itis.jlab.services.banks_site_parsing;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.itis.jlab.model.Bank;
import ru.itis.jlab.model.EdgeCurrency;
import ru.itis.jlab.repositories.BankRepository;
import ru.itis.jlab.repositories.EdgeCurrencyRepository;
import ru.itis.jlab.services.matrix.MatrixService;
import ru.itis.jlab.services.modelServices.BankService;
import ru.itis.jlab.services.modelServices.EdgeCurrencyService;

import java.util.List;
import java.util.Optional;

@Service
public class ParsingCurrencyServiceImpl implements ParsingCurrencyService {

    @Autowired
    EdgeCurrencyService edgeCurrencyService;

    @Autowired
    BankService bankService;

    @Override
    public void parsingBanksSite() {
        List<Bank> banks = bankService.findAll();
        for (Bank bank : banks) {
            parsingCurrencyFromBank(bank);
        }
    }

    @Autowired
    MatrixService matrixService;

    @Autowired
    FindCurrencyCostService findCurrencyCostService;

    @Override
    public void parsingCurrencyFromBank(Bank bank) {
        List<EdgeCurrency> currencies = edgeCurrencyService.findAllByBankId(bank.getId());
        for (EdgeCurrency edgeCurrency : currencies) {
            Optional<Double> optionalNewCostByOne = findCurrencyCostService.findCurrencyCostByOneByEdgeCurrency(edgeCurrency);
            if (optionalNewCostByOne.isPresent()) {
                Double newCostByOne = optionalNewCostByOne.get();
                edgeCurrency.setCostByOne(newCostByOne);
                edgeCurrency.setLogCostByOne(-Math.log(newCostByOne));
                matrixService.updateMatrix(edgeCurrency);
            } else {
                throw new IllegalArgumentException("Невозможно правивльно узнать новую стоимость валюты. Проверьте XPath и url валюты c id - " + edgeCurrency.getId());
            }
        }
    }

}

package ru.itis.jlab.services.banks_site_parsing;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.itis.jlab.model.Bank;
import ru.itis.jlab.model.EdgeCurrency;
import ru.itis.jlab.repositories.BankRepository;
import ru.itis.jlab.repositories.EdgeCurrencyRepository;
import ru.itis.jlab.services.matrix.MatrixService;

import java.util.List;
import java.util.Optional;

@Service
public class ParsingCurrencyService {

    @Autowired
    EdgeCurrencyRepository edgeCurrencyRepository;

    @Autowired
    BankRepository bankRepository;

    @Scheduled(fixedDelay = 1000)
    public void parsingBanksSite() {
        List<Bank> banks = bankRepository.findAll();
        for (Bank bank : banks) {
            parsingCurrencyFromBank(bank);
        }
    }

    @Autowired
    MatrixService matrixService;

    public void parsingCurrencyFromBank(Bank bank) {
        List<EdgeCurrency> currencies = edgeCurrencyRepository.findAllByBankId(bank.getId());
        for (EdgeCurrency edgeCurrency : currencies) {
            Optional<Double> optionalNewCostByOne = findCurrencyCostByOneByEdgeCurrency(edgeCurrency);
            if (optionalNewCostByOne.isPresent()) {
                Double newCostByOne = optionalNewCostByOne.get();
                matrixService.updateMatrix(edgeCurrency, newCostByOne);
            } else {
                throw new IllegalArgumentException("Невозможно правивльно узнать новую стоимость валюты. Проверьте XPath и url валюты c id - " + edgeCurrency.getId());
            }
        }
    }

    public Optional<Double> findCurrencyCostByOneByEdgeCurrency(EdgeCurrency edgeCurrency) {
        String url = edgeCurrency.getUrlFromData();
        String xPath = edgeCurrency.getParsingXPath();
        Optional<Double> optionalCostByOne = findCurrencyCostByOne(url, xPath);
        if (optionalCostByOne.isPresent()) {
            double costByOne = optionalCostByOne.get();
            if (edgeCurrency.getReverse()) {
                costByOne = 1. / costByOne;
            }
            return Optional.of(costByOne);
        } else {
            return Optional.empty();
        }
    }

    @Autowired
    WebDriver driver;

    synchronized private Optional<Double> findCurrencyCostByOne(String url, String xPath) {
        driver.get(url);
        By searchBoxById = By.xpath(xPath);
        WebElement searchBox = driver.findElement(searchBoxById);
        String willDouble = searchBox.getText().replaceAll(",", ".");
        return Optional.ofNullable(Double.parseDouble(willDouble));
    }
}

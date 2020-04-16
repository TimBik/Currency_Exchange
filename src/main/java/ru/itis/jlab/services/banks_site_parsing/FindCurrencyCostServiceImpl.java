package ru.itis.jlab.services.banks_site_parsing;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.jlab.model.EdgeCurrency;

import java.util.Optional;
@Service
public class FindCurrencyCostServiceImpl implements FindCurrencyCostService {

    @Override
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

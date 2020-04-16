package ru.itis.jlab.services.banks_site_parsing;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import ru.itis.jlab.model.EdgeCurrency;

import java.util.Optional;

public interface FindCurrencyCostService {

     Optional<Double> findCurrencyCostByOneByEdgeCurrency(EdgeCurrency edgeCurrency);
}

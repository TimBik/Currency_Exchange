package ru.itis.jlab.services.banks_site_parsing;

import org.springframework.scheduling.annotation.Scheduled;
import ru.itis.jlab.model.Bank;
import ru.itis.jlab.model.EdgeCurrency;

import java.util.Optional;

public interface ParsingCurrencyService {

    @Scheduled(fixedDelay = 1000 * 60 * 5)
    void parsingBanksSite();

    void parsingCurrencyFromBank(Bank bank);
}

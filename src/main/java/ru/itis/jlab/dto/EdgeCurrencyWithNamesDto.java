package ru.itis.jlab.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import ru.itis.jlab.model.Bank;
import ru.itis.jlab.model.Currency;
import ru.itis.jlab.model.EdgeCurrency;
import ru.itis.jlab.services.modelServices.BankService;
import ru.itis.jlab.services.modelServices.CurrencyService;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EdgeCurrencyWithNamesDto {
    private long id;
    private String bankName;
    private String nameCurrencyFrom;
    private String nameCurrencyTo;
    private String urlFromData;
    private String parsingXPath;

    //подумать не может ли привести к микроошибкам
    //поменять на более точный инструмент
    private double costByOne;


}

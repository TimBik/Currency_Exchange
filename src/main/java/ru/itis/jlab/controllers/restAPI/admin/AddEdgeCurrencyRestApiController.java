package ru.itis.jlab.controllers.restAPI.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.jlab.dto.EdgeCurrencyWithNamesDto;
import ru.itis.jlab.model.Bank;
import ru.itis.jlab.model.Currency;
import ru.itis.jlab.model.EdgeCurrency;
import ru.itis.jlab.services.modelServices.BankService;
import ru.itis.jlab.services.modelServices.CurrencyService;
import ru.itis.jlab.services.modelServices.EdgeCurrencyService;

import java.util.Optional;

@RestController
public class AddEdgeCurrencyRestApiController {

    @Autowired
    EdgeCurrencyService edgeCurrencyService;

    @Autowired
    BankService bankService;

    @Autowired
    CurrencyService currencyService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/admin/addEdgeCurrencyRestApi")
    public ResponseEntity<String> saveEdgeCurrencyRestApi(@RequestBody() EdgeCurrencyWithNamesDto edgeCurrencyWithNamesDto) {
        //TO DO заменить на нормально создание объекта через spring
        // просто перетащи в конструктор EdgeCurrencyWithNameDto(EdgeCurrency)
        String nameCurrencyFrom = edgeCurrencyWithNamesDto.getNameCurrencyFrom();
        Optional<Currency> currencyFrom = currencyService.findByCurrencyName(nameCurrencyFrom);

        String nameCurrencyTo = edgeCurrencyWithNamesDto.getNameCurrencyTo();
        Optional<Currency> currencyTo = currencyService.findByCurrencyName(nameCurrencyTo);

        String nameBank = edgeCurrencyWithNamesDto.getBankName();
        Optional<Bank> optionalBank = bankService.findByName(nameBank);

        String status;
        if (optionalBank.isPresent() && currencyFrom.isPresent() && currencyTo.isPresent()) {
            edgeCurrencyService.save(EdgeCurrency.builder()
                    .CurrencyFrom(currencyFrom.get())
                    .CurrencyTo(currencyTo.get())
                    .bank(optionalBank.get())
                    .urlFromData(edgeCurrencyWithNamesDto.getUrlFromData())
                    .parsingXPath(edgeCurrencyWithNamesDto.getParsingXPath())
                    .reverse(edgeCurrencyWithNamesDto.getReverse() != null)
                    .build());
            status = "добавление ребра валют прошло успешно";
        } else {
            status = "добавление ребра валют невозможно. Проверьте введенные данные";
        }
        return ResponseEntity.ok(status);
    }
}

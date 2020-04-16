package ru.itis.jlab.controllers.restAPI.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.jlab.dto.TokenDto;
import ru.itis.jlab.model.Currency;
import ru.itis.jlab.services.modelServices.CurrencyService;

import java.util.Optional;

@RestController
public class AddCurrencyRestApiController {

    @Autowired
    CurrencyService currencyService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/admin/addCurrencyRestApi")
    public ResponseEntity<String> saveCurrencyRestApi(@RequestBody() Currency currency) {
        String status;
        Optional<Currency> optionalCurrency = currencyService.findByCurrencyName(currency.getName());
        if (!optionalCurrency.isPresent()) {
            currencyService.save(currency);
            status = "Добавление валюты прошло успешно";
        } else {
            status = "Добавление валюты провалилось. Валюта с таким названием уже существует";
        }
        return ResponseEntity.ok(status);
    }
}

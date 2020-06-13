package ru.itis.jlab.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.jlab.model.Currency;
import ru.itis.jlab.services.modelServices.CurrencyService;

import java.util.Optional;

@Controller
public class AddCurrencyController {

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/admin/addCurrency", method = RequestMethod.GET)
    public ModelAndView addCurrency() {
        ModelAndView modelAndView = new ModelAndView("addCurrency");
        return modelAndView;
    }

    @Autowired
    CurrencyService currencyService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/admin/addCurrency", method = RequestMethod.POST)
    public ModelAndView saveCurrency(@ModelAttribute("currencyForm") Currency currency) {
        ModelAndView modelAndView = new ModelAndView("addCurrency");
        Optional<Currency> optionalCurrency = currencyService.findByCurrencyName(currency.getName());
        if(!optionalCurrency.isPresent()) {
            currencyService.save(currency);
            modelAndView.addObject("status", "добавление валюты прошло успешно");
        }else {
            modelAndView.addObject("status", "Добавление валюты провалилось. Валюта с таким названием уже существует");
        }
        return modelAndView;
    }
}

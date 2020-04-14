package ru.itis.jlab.controllers;


import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.jlab.model.Bank;
import ru.itis.jlab.model.Currency;
import ru.itis.jlab.model.EdgeCurrency;
import ru.itis.jlab.services.modelServices.BankService;
import ru.itis.jlab.services.modelServices.EdgeCurrencyService;

import java.util.List;
import java.util.Map;

@Controller
public class MainController {

    @Autowired
    BankService bankService;

    //    @PreAuthorize("isAuthenticated()")
    @PreAuthorize("permitAll()")
    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public ModelAndView main() {
        ModelAndView modelAndView = new ModelAndView("main");
        List<Bank> banks = bankService.findAll();
        modelAndView.addObject("banks", banks);
        return modelAndView;
    }
}

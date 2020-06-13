package ru.itis.jlab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.jlab.dto.EdgeCurrencyWithNamesDto;
import ru.itis.jlab.model.Currency;
import ru.itis.jlab.services.modelServices.CurrencyService;
import ru.itis.jlab.services.matrix.PathCurrencyExchangeService;

import java.util.List;
import java.util.Optional;

@Controller
public class FinderController {

    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    @RequestMapping(value = "/finder", method = RequestMethod.GET)
    public ModelAndView finder() {
        ModelAndView modelAndView = new ModelAndView("finder");
        return modelAndView;
    }

    @Autowired
    CurrencyService currencyService;

    @Autowired
    PathCurrencyExchangeService pathCurrencyExchangeService;


    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    @RequestMapping(value = "/finder", method = RequestMethod.POST)
    public ModelAndView finder(@ModelAttribute(name = "currencyFrom") String currencyFrom,
                               @ModelAttribute(name = "currencyTo") String currencyTo) {
        Optional<Currency> optionalCurrencyFrom = currencyService.findByCurrencyName(currencyFrom);
        Optional<Currency> optionalCurrencyTo = currencyService.findByCurrencyName(currencyTo);
        ModelAndView modelAndView = new ModelAndView("finder");
        if (optionalCurrencyFrom.isPresent() && optionalCurrencyTo.isPresent()) {
            List<EdgeCurrencyWithNamesDto> edgeCurrencyList = pathCurrencyExchangeService.findBestPath(optionalCurrencyFrom.get(), optionalCurrencyTo.get());
            if (edgeCurrencyList.size() > 0) {
                modelAndView.addObject("edgeCurrencyList", edgeCurrencyList);
            } else {
                modelAndView.addObject("status", "Невозможно найти путь обмена валют. Возможно нет банков готовых произвести обмен");
            }
        } else {
            modelAndView.addObject("status", "Невозможно идентифицировать введные валюты. Проверьте их написание или их нет в базе данных");
        }
        return modelAndView;
    }
}

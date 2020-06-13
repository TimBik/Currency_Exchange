package ru.itis.jlab.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.jlab.dto.EdgeCurrencyWithNamesDto;
import ru.itis.jlab.model.Bank;
import ru.itis.jlab.model.Currency;
import ru.itis.jlab.model.EdgeCurrency;
import ru.itis.jlab.services.banks_site_parsing.FindCurrencyCostService;
import ru.itis.jlab.services.matrix.MatrixService;
import ru.itis.jlab.services.modelServices.BankService;
import ru.itis.jlab.services.modelServices.CurrencyService;
import ru.itis.jlab.services.modelServices.EdgeCurrencyService;

import java.util.Optional;

@Controller
public class AddEdgeCurrencyController {
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/admin/addEdgeCurrency", method = RequestMethod.GET)
    public ModelAndView addEdgeCurrently() {
        ModelAndView modelAndView = new ModelAndView("addEdgeCurrency");
        return modelAndView;
    }

    @Autowired
    EdgeCurrencyService edgeCurrencyService;

    @Autowired
    BankService bankService;

    @Autowired
    CurrencyService currencyService;

    @Autowired
    MatrixService matrixService;

    @Autowired
    FindCurrencyCostService findCurrencyCostService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/admin/addEdgeCurrency", method = RequestMethod.POST)
    public ModelAndView saveBank(@ModelAttribute("currencyForm") EdgeCurrencyWithNamesDto edgeCurrencyWithNamesDto) {
        //TO DO заменить на нормально создание объекта через spring
        // просто перетащи в конструктор EdgeCurrencyWithNameDto(EdgeCurrency)
        String nameCurrencyFrom = edgeCurrencyWithNamesDto.getNameCurrencyFrom();
        Optional<Currency> currencyFrom = currencyService.findByCurrencyName(nameCurrencyFrom);

        String nameCurrencyTo = edgeCurrencyWithNamesDto.getNameCurrencyTo();
        Optional<Currency> currencyTo = currencyService.findByCurrencyName(nameCurrencyTo);

        String nameBank = edgeCurrencyWithNamesDto.getBankName();
        Optional<Bank> optionalBank = bankService.findByName(nameBank);

        ModelAndView modelAndView = new ModelAndView("addEdgeCurrency");

        if (optionalBank.isPresent() && currencyFrom.isPresent() && currencyTo.isPresent()) {
            EdgeCurrency edgeCurrency = EdgeCurrency.builder()
                    .CurrencyFrom(currencyFrom.get())
                    .CurrencyTo(currencyTo.get())
                    .bank(optionalBank.get())
                    .urlFromData(edgeCurrencyWithNamesDto.getUrlFromData())
                    .parsingXPath(edgeCurrencyWithNamesDto.getParsingXPath())
                    .reverse(edgeCurrencyWithNamesDto.getReverse() != null)
                    .build();
            Optional<Double> optionalCostByOne = findCurrencyCostService.findCurrencyCostByOneByEdgeCurrency(edgeCurrency);
            if (optionalCostByOne.isPresent()) {
                Double cost = optionalCostByOne.get();
                edgeCurrency.setCostByOne(cost);
                edgeCurrency.setLogCostByOne(-Math.log(cost));
                edgeCurrencyService.save(edgeCurrency);
                matrixService.updateMatrix(edgeCurrency);
                modelAndView.addObject("status", "добавление ребра валют прошло успешно");
            } else {
                modelAndView.addObject("status", "Невозможно правивльно узнать новую стоимость валюты. Проверьте xpath новой валюты по url - " + edgeCurrencyWithNamesDto.getUrlFromData());
            }
        } else {
            modelAndView.addObject("status", "добавление ребра валют невозможно. Проверьте введенные данные");
        }
        return modelAndView;
    }
}

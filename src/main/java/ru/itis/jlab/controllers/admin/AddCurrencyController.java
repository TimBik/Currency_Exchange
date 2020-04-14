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
    public ModelAndView saveBank(@ModelAttribute("currencyForm") Currency currency) {
        //TO DO заменить на нормально создание объекта через spring
        currencyService.save(currency);
        ModelAndView modelAndView = new ModelAndView("addCurrency");
        modelAndView.addObject("status", "добавление банка прошло успешно");
        return modelAndView;
    }
}

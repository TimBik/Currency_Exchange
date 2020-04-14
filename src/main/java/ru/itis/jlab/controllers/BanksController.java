package ru.itis.jlab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.jlab.model.Bank;
import ru.itis.jlab.services.modelServices.BankService;

import java.util.Optional;

@Controller
public class BanksController {

    @Autowired
    BankService bankService;

    @PreAuthorize("permitAll()")
    @RequestMapping(value = "banks/{bank-name}", method = RequestMethod.GET)
    public ModelAndView showBank(@PathVariable("bank-name") String bankName) {
        ModelAndView modelAndView = new ModelAndView("bank");
        Optional<Bank> bank = bankService.findByName(bankName);
        if (bank.isPresent()) {
            modelAndView.addObject("bank", bank.get());
        } else {
            modelAndView.addObject("status", "Извините, мы не следим за данным банком");
        }
        return modelAndView;
    }
}

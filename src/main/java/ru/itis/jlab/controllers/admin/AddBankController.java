package ru.itis.jlab.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.jlab.model.Bank;
import ru.itis.jlab.services.modelServices.BankService;

import java.util.Optional;

@Controller
public class AddBankController {
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/admin/addBank", method = RequestMethod.GET)
    public ModelAndView addBank() {
        ModelAndView modelAndView = new ModelAndView("addBank");
        return modelAndView;
    }

    @Autowired
    BankService bankService;
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/admin/addBank", method = RequestMethod.POST)
    public ModelAndView saveBank(@ModelAttribute(value = "nameBank") String bankName) {
        ModelAndView modelAndView = new ModelAndView("addBank");
        Optional<Bank> bank = bankService.findByName(bankName);
        if(!bank.isPresent()) {
            bankService.save(Bank.builder().name(bankName).build());
            modelAndView.addObject("status", "добавление банка прошло успешно");
        }else {
            modelAndView.addObject("status", "Добавление банка провалилось. Такой банк уже существует");
        }
        return modelAndView;
    }
}

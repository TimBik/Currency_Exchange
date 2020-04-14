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
        //TO DO заменить на нормально создание объекта через spring
        bankService.save(Bank.builder().name(bankName).build());
        ModelAndView modelAndView = new ModelAndView("addBank");
        modelAndView.addObject("status", "добавление банка прошло успешно");
        return modelAndView;
    }
}

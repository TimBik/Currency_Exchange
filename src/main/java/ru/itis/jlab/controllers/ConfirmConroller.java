package ru.itis.jlab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.jlab.services.SignService.SignUpService;

@Controller
public class ConfirmConroller {

    @Autowired
    SignUpService signUpService;

    @PreAuthorize("permitAll()")
    @RequestMapping(value = "/confirm/{code}", method = RequestMethod.GET)
    public ModelAndView updateStep(@PathVariable("code") String code) {
        signUpService.chageStateAccept(code);
        ModelAndView modelAndView = new ModelAndView("redirect:/signIn");
        return modelAndView;
    }
}

package ru.itis.jlab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.jlab.dto.SignUpDto;
import ru.itis.jlab.services.SignService.SignUpService;

@Controller
public class SignUpController {
    @Autowired
    private SignUpService signUpService;

//    @PreAuthorize("!isAuthenticated()")
    @RequestMapping(value = "/signUp", method = RequestMethod.GET)
    public ModelAndView signUp(Authentication authentication) {
        ModelAndView modelAndView = new ModelAndView();
        if (authentication != null) {
            modelAndView.setViewName("redirect:/main");
        } else {
            modelAndView.setViewName("signUp");
        }
        return modelAndView;
    }

//    @PreAuthorize("!isAuthenticated()")
    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    public ModelAndView signUp(@ModelAttribute(name = "userObj") SignUpDto signUpData) {
        signUpService.loadUserFromParameters(signUpData);
        ModelAndView modelAndView = new ModelAndView("redirect:/signIn");
        return modelAndView;
    }
}

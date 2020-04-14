package ru.itis.jlab.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelpController {
    @PreAuthorize("permitAll()")
    @RequestMapping(value = "/help", method = RequestMethod.GET)
    public ModelAndView help() {
        ModelAndView modelAndView = new ModelAndView("help");
        return modelAndView;
    }
}

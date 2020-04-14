package ru.itis.jlab.controllers.admin;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AddAdminController {
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/admin/addAdmin", method = RequestMethod.GET)
    public ModelAndView addAdmin() {
        ModelAndView modelAndView = new ModelAndView("addAdmin");
        return modelAndView;
    }
}

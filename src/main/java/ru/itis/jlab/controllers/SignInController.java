package ru.itis.jlab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.jlab.dto.SignInDto;
import ru.itis.jlab.dto.TokenDto;
import ru.itis.jlab.services.SignService.SignInService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
public class SignInController {

    @RequestMapping(value = "/signIn", method = RequestMethod.GET)
    public ModelAndView signIn() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("signIn");
        return modelAndView;
    }

    @Autowired
    SignInService signInService;

    @RequestMapping(value = "/signIn", method = RequestMethod.POST)
    public ModelAndView signIn(HttpServletResponse response,
                               @RequestParam(value = "email") String mail,
                               @RequestParam(value = "password") String password) {
        TokenDto tokenDto = signInService.signIn(SignInDto.builder().email(mail).password(password).build());
        Cookie cookie = new Cookie("myToken", tokenDto.getToken());
        response.addCookie(cookie);
        ModelAndView modelAndView = new ModelAndView("redirect:/main");
        return modelAndView;
    }

}

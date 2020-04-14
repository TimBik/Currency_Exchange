package ru.itis.jlab.security.jwt.filter;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import ru.itis.jlab.security.jwt.authentication.JwtAuthentication;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("jwtAuthenticationFilter")
public class JwtAuthenticationFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        // преобразуем запрос в HTTP
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        // получаем токен
        String token = request.getHeader("Authorization");
        if (token == null) {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("token")) {
                        token = cookie.getValue();
                        break;
                    }
                }
            }
        }
        // создаем объект аутентификации
        Authentication authentication = new JwtAuthentication(token);
        // кладем его в контекст для текущего потока
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // отправили запрос дальше
        filterChain.doFilter(servletRequest, servletResponse);
    }
}

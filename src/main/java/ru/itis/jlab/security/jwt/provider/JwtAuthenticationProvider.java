package ru.itis.jlab.security.jwt.provider;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ru.itis.jlab.model.Role;
import ru.itis.jlab.model.State;
import ru.itis.jlab.model.User;
import ru.itis.jlab.security.jwt.authentication.JwtAuthentication;
import ru.itis.jlab.security.jwt.details.UserDetailsImpl;


// проверить аутентификацию пользователя
@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {
    // секретный ключ, которым мы подписываем токен
    @Value("${jwt.secret}")
    private String secret;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String token = authentication.getName();
        UserDetails userDetails;
        User user;
        if (token != null) {
            Claims claims;
            try {
                // выполняю парсинг токена со своим секретным ключом
                claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token)
                        .getBody();
            } catch (Exception e) {
                throw new AuthenticationCredentialsNotFoundException("Bad token");
            }
            // создаем UserDetails
            user = User.builder()
                    .id(Long.parseLong(claims.get("sub", String.class)))
                    .role(Role.valueOf(claims.get("role", String.class)))
                    .login(claims.get("login", String.class))
                    .state(State.valueOf(claims.get("state", String.class)))
                    .build();

        } else {
            user = User.builder().role(Role.ANONIM).build();
        }
        userDetails = new UserDetailsImpl(user);


//        // аутентификация прошла успешно
        authentication.setAuthenticated(true);
        // положили в эту аутентификацию пользователя
        ((JwtAuthentication) authentication).setUserDetails(userDetails);
        return authentication;
    }

    // проверяет, подходит ли текущий провайдер для этой аутентификации
    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthentication.class.equals(authentication);
    }
}

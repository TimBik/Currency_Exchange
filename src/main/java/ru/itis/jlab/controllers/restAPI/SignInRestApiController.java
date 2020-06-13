package ru.itis.jlab.controllers.restAPI;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.jlab.dto.SignInDto;
import ru.itis.jlab.dto.TokenDto;
import ru.itis.jlab.services.SignService.SignInService;

@RestController
public class SignInRestApiController {

    @Autowired
    private SignInService signInService;

    @PostMapping("/signInRestApi")
    public ResponseEntity<TokenDto> signIn(@RequestBody SignInDto signInData) {
        return ResponseEntity.ok(signInService.signIn(signInData));
    }
}



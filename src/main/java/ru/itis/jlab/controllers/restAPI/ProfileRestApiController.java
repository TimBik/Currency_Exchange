package ru.itis.jlab.controllers.restAPI;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.jlab.dto.UserDto;
import ru.itis.jlab.security.jwt.details.UserDetailsImpl;

@RestController
public class ProfileRestApiController {

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/selfRestApi")
    // TODO: сделать через getSelf(@AuthenticationPrincipal UserDetailsImpl) + 10 баллов к экзамену первому
    public ResponseEntity<UserDto> getSelf() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getDetails();
        System.out.println(userDetails);
        return ResponseEntity.ok(UserDto.builder()
                .login(userDetails.getUsername())
                .id(userDetails.getUser().getId())
                .build());
    }
}



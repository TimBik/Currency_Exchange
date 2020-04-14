package ru.itis.jlab.services.SignService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.jlab.dto.SignUpDto;
import ru.itis.jlab.dto.UserDto;
import ru.itis.jlab.model.Role;
import ru.itis.jlab.model.State;
import ru.itis.jlab.model.User;
import ru.itis.jlab.repositories.UserRepository;
import ru.itis.jlab.services.MailService.MailService;
import ru.itis.jlab.services.MailService.PrepareMailModelService;

import java.util.Optional;
import java.util.UUID;

@Service
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private MailService mailService;
    @Autowired
    private UserRepository usersRepository;
    @Autowired
    private PrepareMailModelService prepareMailModelService;

    @Override
    public UserDto loadUserFromParameters(SignUpDto userData) {
        User user = User.builder()
                .mail(userData.getMail())
                .hashPassword(passwordEncoder.encode(userData.getPassword()))
                .state(State.NOT_CONFIRMED)
                .role(Role.USER)
                .confirmCode(UUID.randomUUID().toString())
                .login(userData.getLogin())
                .build();

        usersRepository.save(user);

        mailService.sendMessage("Confirm", user.getMail(), prepareMailModelService.prepareMailModel(user.getConfirmCode(), user.getLogin()), "mail.ftl");

        return UserDto.from(user);
    }

    @Override
    public void chageStateAccept(String code) {
        Optional<User> optionalUser = usersRepository.findByConfirmCode(code);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setState(State.CONFIRMED);
            usersRepository.update(user);
        }
    }
}

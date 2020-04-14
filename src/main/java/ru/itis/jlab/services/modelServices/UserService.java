package ru.itis.jlab.services.modelServices;

import ru.itis.jlab.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getAllUsers();

    void deleteUser(Long userId);
}

package ru.itis.jlab.services.modelServices;

import ru.itis.jlab.dto.MessageDto;
import ru.itis.jlab.model.Message;

import java.util.List;

public interface MessageService {
    public List<Message> findAllByBankName(String bankName);

    void save(MessageDto message);
}

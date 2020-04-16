package ru.itis.jlab.services.modelServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.jlab.dto.MessageDto;
import ru.itis.jlab.model.Message;
import ru.itis.jlab.repositories.MessageRepository;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageRepository messageRepository;

    public List<Message> findAllByBankName(String bankName) {
        return messageRepository.findAllByBankName(bankName);
    }

    @Override
    public void save(MessageDto message) {
        messageRepository.save(Message.builder()
                .bankName(message.getBankName())
                .text(message.getText())
                .build());
    }
}

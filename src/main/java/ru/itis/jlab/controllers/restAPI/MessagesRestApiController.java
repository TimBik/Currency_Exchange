package ru.itis.jlab.controllers.restAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.itis.jlab.dto.MessageDto;
import ru.itis.jlab.dto.RoomDto;
import ru.itis.jlab.services.modelServices.MessageService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MessagesRestApiController {

    private static final Map<String, Map<String, List<MessageDto>>> messages = new HashMap<>();
    @Autowired
    private MessageService messageService;

    // получили сообщение от какой либо страницы - мы его разошлем во все другие страницы
    @PreAuthorize("permitAll()")
    @PostMapping("/messages")
    public ResponseEntity<Object> receiveMessage(@RequestBody MessageDto message) {
        // если сообщений с этой или для этой страницы еще не было
        if (!messages.containsKey(message.getBankName())) {
            messages.put(message.getBankName(), new HashMap<>());
        }
        Map<String, List<MessageDto>> messagesFromBank = messages.get(message.getBankName());
        if (!messagesFromBank.containsKey(message.getPageId())) {
            // добавляем эту страницу в Map-у страниц
            messagesFromBank.put(message.getPageId(), new ArrayList(messageService.findAllByBankName(message.getBankName())));
        }
        // полученное сообщение добавляем для всех открытых страниц нашего приложения
        if (!message.getText().equals("Login")) {
            messageService.save(message);
            for (List<MessageDto> pageMessages : messagesFromBank.values()) {
                // перед тем как положить сообщение для какой-либо страницы
                // мы список сообщений блокируем
                synchronized (pageMessages) {
                    // добавляем сообщение
                    pageMessages.add(message);
                    // говорим, что другие потоки могут воспользоваться этим списком
                    pageMessages.notifyAll();
                }
            }
        }
        return ResponseEntity.ok().build();
    }

    // получить все сообщения для текущего запроса
    @PreAuthorize("permitAll()")
    @PostMapping("/messagesShow")
    public ResponseEntity<List<MessageDto>> getMessagesForPage(@RequestBody RoomDto roomDto) {
        // получили список сообшений для страницы и заблокировали его
        Map<String, List<MessageDto>> messagesFromBank = messages.get(roomDto.getBankName());
        synchronized (messagesFromBank.get(roomDto.getPageId())) {
            // если нет сообщений уходим в ожидание
            if (messagesFromBank.get(roomDto.getPageId()).isEmpty()) {
                try {
                    messagesFromBank.get(roomDto.getPageId()).wait();
                } catch (InterruptedException e) {
                    throw new IllegalArgumentException(e);
                }
            }
        }

        // если сообщения есть - то кладем их в новых список
        List<MessageDto> response = new ArrayList<>(messagesFromBank.get(roomDto.getPageId()));
        // удаляем сообщения из мапы
        messagesFromBank.get(roomDto.getPageId()).remove(0);
        return ResponseEntity.ok(response);
    }
}
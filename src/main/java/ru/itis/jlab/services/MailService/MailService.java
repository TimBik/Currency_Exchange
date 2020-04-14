package ru.itis.jlab.services.MailService;

import java.util.Map;

public interface MailService {
    void sendMessage(String subject, String mail, Map model, String ftl);
}

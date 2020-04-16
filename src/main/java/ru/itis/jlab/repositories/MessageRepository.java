package ru.itis.jlab.repositories;

import ru.itis.jlab.model.Message;
import ru.itis.jlab.repositories.CrudRepository;

import java.util.List;

public interface MessageRepository extends CrudRepository<Long, Message> {
    List<Message> findAllByBankName(String bankName);
}

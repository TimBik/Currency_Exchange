package ru.itis.jlab.services.modelServices;

import org.springframework.stereotype.Service;
import ru.itis.jlab.model.Bank;

import java.util.List;
import java.util.Optional;

public interface BankService{
    void save(Bank bank);

    Optional<Bank> findByName(String nameBank);

    Optional<Bank> find(long id);

    List<Bank> findAll();
}

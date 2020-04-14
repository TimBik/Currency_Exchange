package ru.itis.jlab.services.modelServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.jlab.model.Bank;
import ru.itis.jlab.repositories.BankRepository;

import java.util.List;
import java.util.Optional;


@Service
public class BankServiceImpl implements BankService {
    @Autowired
    BankRepository bankRepository;


    @Override
    public void save(Bank bank) {
        bankRepository.save(bank);
    }

    @Override
    public Optional<Bank> findByName(String nameBank) {
        return bankRepository.findByName(nameBank);
    }

    @Override
    public Optional<Bank> find(long id) {
        return bankRepository.find(id);
    }

    @Override
    public List<Bank> findAll() {
        return bankRepository.findAll();
    }
}

package ru.itis.jlab.repositories;

import ru.itis.jlab.model.Bank;

import java.util.Optional;

public interface BankRepository extends CrudRepository<Long, Bank>{
    Optional<Bank> findByName(String name);

}

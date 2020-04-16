package ru.itis.jlab.controllers.restAPI.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.jlab.dto.SignInDto;
import ru.itis.jlab.dto.TokenDto;
import ru.itis.jlab.model.Bank;
import ru.itis.jlab.services.modelServices.BankService;

import java.util.Optional;

@RestController
public class AddBankRestApiController {

    @Autowired
    BankService bankService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/admin/addBankRestApi")
    public ResponseEntity<String> saveBankRestApi(@RequestBody() Bank bank) {
        String status;
        String bankName = bank.getName();
        Optional<Bank> optionalBank = bankService.findByName(bankName);
        if (!optionalBank.isPresent()) {
            bankService.save(Bank.builder().name(bankName).build());
            status = "добавление банка прошло успешно";
        } else {
            status = "Добавление банка провалилось. Такой банк уже существует";
        }
        return ResponseEntity.ok(status);
    }
}

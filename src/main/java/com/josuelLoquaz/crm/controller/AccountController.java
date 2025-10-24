package com.josuelLoquaz.crm.controller;

import com.josuelLoquaz.crm.domain.Accounts;
import com.josuelLoquaz.crm.repository.AccountsRepository;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    private AccountsRepository accountsRepository;

    public AccountController(AccountsRepository accountsRepository){
        this.accountsRepository=accountsRepository;
    }
    @GetMapping("/{id}")
    public Accounts getAccountById(@PathVariable UUID id) {
        // Usa o repositório para buscar uma conta pelo ID.
        // Se não encontrar, retorna null (o que o frontend tratará como "não encontrado").
        return accountsRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Accounts addAccounts(@RequestBody Accounts accounts){
        return accountsRepository.save(accounts);
    }
    @GetMapping
    public List<Accounts> listAccount(){
        return accountsRepository.findAll();
    }

    @PutMapping("/{id}")
    public void editAccount(@PathVariable("id") UUID id,@RequestBody Accounts accounts){
        accounts.setId(id);
        accountsRepository.save(accounts);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id")UUID id){
        accountsRepository.deleteById(id);
    }

}

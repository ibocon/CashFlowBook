package com.ibocon.ledger.controller;

import java.util.List;
import java.util.Optional;

import com.ibocon.ledger.annotation.CurrentUser;
import com.ibocon.ledger.model.Account;
import com.ibocon.ledger.model.User;
import com.ibocon.ledger.model.payload.AccountRequest;
import com.ibocon.ledger.repository.AccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping(path="/account", produces="application/json")
public class AccountController {
    @Autowired
    private AccountRepository accountRepository;

    @GetMapping
    public ResponseEntity<?> read(@CurrentUser User user) {
        List<Account> accounts = accountRepository.findByUser(user);
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@CurrentUser User user, @RequestBody AccountRequest accountRequest ) {
        Account account = new Account(user);
        account.setBase(accountRequest.getBase());
        account.setName(accountRequest.getName());
        account = accountRepository.save(account);
        return new ResponseEntity<>(account, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> update(@CurrentUser User user, @RequestBody AccountRequest accountRequest) {
        Optional<Account> optionalAccount = accountRepository.findById(accountRequest.getId());
        if(optionalAccount.isPresent()){
            Account account = optionalAccount.get();
            account.setBase(accountRequest.getBase());
            account.setName(accountRequest.getName());
            account = accountRepository.save(account);

            return new ResponseEntity<>(account, HttpStatus.ACCEPTED);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@CurrentUser User user, @RequestBody AccountRequest accountRequest) {
        Optional<Account> optionalAccount = accountRepository.findById(accountRequest.getId());
        if(optionalAccount.isPresent()){
            Account account = optionalAccount.get();
            accountRepository.deleteById(account.getId());
            return new ResponseEntity<>(account, HttpStatus.ACCEPTED);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}
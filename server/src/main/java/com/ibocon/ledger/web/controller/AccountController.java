package com.ibocon.ledger.web.controller;

import java.util.List;

import com.ibocon.ledger.config.auth.CurrentUser;
import com.ibocon.ledger.repository.account.UserDefinedAccount;
import com.ibocon.ledger.repository.account.UserDefinedAccountRepository;
import com.ibocon.ledger.repository.user.User;
import com.ibocon.ledger.web.dto.AccountRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PutMapping;

@RequiredArgsConstructor
@RestController
@RequestMapping(path="/account", produces="application/json")
public class AccountController {
    
    private final UserDefinedAccountRepository accountRepository;

    @GetMapping
    public ResponseEntity<?> read(@CurrentUser User user) {
        List<UserDefinedAccount> accounts = accountRepository.findByBelongTo(user);
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    // @PostMapping
    // public ResponseEntity<?> create(@CurrentUser User user, @RequestBody AccountRequest accountRequest ) {
    //     UserDefinedAccount account = new UserDefinedAccount(accountRequest.getOfficialAccount(), accountRequest.getAccountName());
    //     account = accountRepository.save(account);
    //     return new ResponseEntity<>(account, HttpStatus.CREATED);
    // }

    // @PutMapping
    // public ResponseEntity<?> update(@CurrentUser User user, @RequestBody AccountRequest accountRequest) {
    //     List<UserDefinedAccount> optionalAccount = accountRepository.findByBelongToAndAccountName(user, accountRequest.getAccountName());
    //     if(!optionalAccount.isEmpty()){
    //         UserDefinedAccount account = optionalAccount.get(0);
    //         // TODO 계정 정보를 업데이트하자.
    //         account = accountRepository.save(account);

    //         return new ResponseEntity<>(account, HttpStatus.ACCEPTED);
    //     }else{
    //         return ResponseEntity.notFound().build();
    //     }
    // }

    // @DeleteMapping
    // public ResponseEntity<?> delete(@CurrentUser LedgerUser user, @RequestBody AccountRequest accountRequest) {
    //     Optional<UserDefinedAccount> optionalAccount = accountRepository.findById(accountRequest.getId());
    //     if(optionalAccount.isPresent()){
    //         UserDefinedAccount account = optionalAccount.get();
    //         accountRepository.deleteById(account.getId());
    //         return new ResponseEntity<>(account, HttpStatus.ACCEPTED);
    //     }else{
    //         return ResponseEntity.notFound().build();
    //     }
    // }

}
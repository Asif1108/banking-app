package com.springboot.project.bankingapp.controller;

import com.springboot.project.bankingapp.dto.AccountDto;
import com.springboot.project.bankingapp.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

        private final AccountService accountService;


    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

//    @PostMapping("create")
//    public Account createAccount(@RequestBody Account account)
//    {
//        AccountDto newAccount = AccountMapper.mapToAccountDto(account);
//        AccountDto createdAccount = accountService.createAccount(newAccount);
//        return AccountMapper.maptoAccount(createdAccount);
//    }

    @PostMapping
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto)
    {
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id)
    {
        return new ResponseEntity<>(accountService.getAccountById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDto> deposit(@PathVariable Long id,@RequestBody Map<String, Double> request)
    {
        double amount = request.get("amount");
        AccountDto accountDto=accountService.deposit(id, amount);
        return ResponseEntity.ok(accountDto);
    }

    @PutMapping("/{id}/withdraw")
    public ResponseEntity<AccountDto> deposit(@PathVariable long id, @RequestBody Map<String, Double> request)
    {

        double amount = request.get("amount");
        AccountDto accountid = accountService.getAccountById(id);
        AccountDto accountDto = accountService.withdraw(id, amount);
        return ResponseEntity.ok(accountDto);
    }

    @GetMapping("/all-accounts")
    public ResponseEntity<List<AccountDto>> getAllAccounts()
    {
        List<AccountDto> accounts = accountService.getAllAccounts();
        return ResponseEntity.ok(accounts);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id)
    {
        accountService.deleteAccount(id);
        return new ResponseEntity<>("Account Deleted Successfully", HttpStatus.OK);
    }
}

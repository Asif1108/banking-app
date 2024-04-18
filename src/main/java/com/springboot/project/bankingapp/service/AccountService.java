package com.springboot.project.bankingapp.service;

import com.springboot.project.bankingapp.dto.AccountDto;
import com.springboot.project.bankingapp.entity.Account;
import org.springframework.stereotype.Service;

import java.util.List;


public interface AccountService {

    AccountDto createAccount(AccountDto accountDto);

    AccountDto getAccountById(Long id);

    AccountDto deposit(Long id, double amount);

    AccountDto withdraw(Long id, double amount);

    List<AccountDto> getAllAccounts();

    void deleteAccount(long id);
}

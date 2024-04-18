package com.springboot.project.bankingapp.service.impl;

import com.springboot.project.bankingapp.dto.AccountDto;
import com.springboot.project.bankingapp.entity.Account;
import com.springboot.project.bankingapp.mapper.AccountMapper;
import com.springboot.project.bankingapp.repository.AccountRepository;
import com.springboot.project.bankingapp.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.maptoAccount(accountDto);
        Account savedAccount = accountRepository.save(account);
        //AccountDto accountDto1 = AccountMapper.mapToAccountDto(savedAccount);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    public AccountDto getAccountById(Long id)
    {
        Account getAccount = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account does not exit"));
        return AccountMapper.mapToAccountDto(getAccount);
    }


    public AccountDto deposit(Long id, double amount)
    {
        Account getAccount = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account does not exit"));

            double total = getAccount.getBalance() + amount;
            getAccount.setBalance(total);
            Account savedAccount = accountRepository.save(getAccount);
            return AccountMapper.mapToAccountDto(savedAccount);

    }

    public AccountDto withdraw(Long id, double amount)
    {
        Account getAccount = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account does not exit"));
        if(amount > getAccount.getBalance())
        {
            throw new RuntimeException("INSUFFICIENT BALANCE");
        }
        double total = getAccount.getBalance() - amount;
        getAccount.setBalance(total);
        Account savedAccount = accountRepository.save(getAccount);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> allAccounts = accountRepository.findAll();

        return allAccounts.stream().map(account -> AccountMapper.mapToAccountDto(account)).collect(Collectors.toList());
    }

    @Override
    public void deleteAccount(long id) {
        Account getAccount = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account does not exit"));
        accountRepository.deleteById(id);
    }
}

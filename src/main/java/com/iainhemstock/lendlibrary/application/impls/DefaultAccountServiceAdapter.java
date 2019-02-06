package com.iainhemstock.lendlibrary.application.impls;

import com.iainhemstock.lendlibrary.application.AccountServiceAdapter;
import com.iainhemstock.lendlibrary.application.dto.AccountDTO;
import com.iainhemstock.lendlibrary.application.impls.assembler.AccountDTOAssembler;
import com.iainhemstock.lendlibrary.domain.model.accounts.*;

import java.util.List;

import static java.util.Objects.requireNonNull;

public class DefaultAccountServiceAdapter implements AccountServiceAdapter {

    private AccountRepository accountRepository;
    private AccountFactory accountFactory;
    private AccountDTOAssembler accountDTOAssembler;

    public DefaultAccountServiceAdapter(AccountRepository accountRepository, AccountFactory accountFactory,
                                        AccountDTOAssembler accountDTOAssembler) {
        this.accountRepository = accountRepository;
        this.accountFactory = accountFactory;
        this.accountDTOAssembler = accountDTOAssembler;
        requireNonNull(this.accountRepository, "Account repository is required");
        requireNonNull(this.accountFactory, "Account factory is required");
        requireNonNull(this.accountDTOAssembler, "Existing account DTO assembler is required");
    }

    @Override
    public String openAccount(AccountDTO accountDTO) {
        AccountId accountId = accountRepository.nextId();
        Account newAccount = accountFactory.create(
                accountId.toString(),
                accountDTO.getFirstName(), accountDTO.getLastName(),
                accountDTO.getAddress1(), accountDTO.getAddress2(), accountDTO.getCity(),
                accountDTO.getCounty(), accountDTO.getPostcode(),
                accountDTO.getContactNumber(), accountDTO.getEmail());
        accountRepository.add(newAccount);

        return accountId.toString();
    }

    @Override
    public List<AccountDTO> fetchAllAccounts() {
        List<Account> allAccounts = accountRepository.getAll();
        return accountDTOAssembler.toDTOList(allAccounts);
    }

    @Override
    public AccountDTO fetchAccount(String accountId) throws AccountNotFoundException {
        requireNonNull(accountId, "Account id is required");
        Account account = accountRepository.getById(new AccountId(accountId));
        return accountDTOAssembler.toDTO(account);
    }
}

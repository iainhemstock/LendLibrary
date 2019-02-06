package com.iainhemstock.lendlibrary.application.impls;

import com.iainhemstock.lendlibrary.application.AccountServiceAdapter;
import com.iainhemstock.lendlibrary.application.dto.ExistingAccountDTO;
import com.iainhemstock.lendlibrary.application.dto.NewAccountDTO;
import com.iainhemstock.lendlibrary.application.impls.assembler.ExistingAccountDTOAssembler;
import com.iainhemstock.lendlibrary.domain.model.accounts.*;

import java.util.Collections;
import java.util.List;

import static java.util.Objects.requireNonNull;

public class DefaultAccountServiceAdapter implements AccountServiceAdapter {

    private AccountRepository accountRepository;
    private AccountFactory accountFactory;
    private ExistingAccountDTOAssembler existingAccountDTOAssembler;

    public DefaultAccountServiceAdapter(AccountRepository accountRepository, AccountFactory accountFactory,
                                        ExistingAccountDTOAssembler existingAccountDTOAssembler) {
        this.accountRepository = accountRepository;
        this.accountFactory = accountFactory;
        this.existingAccountDTOAssembler = existingAccountDTOAssembler;
        requireNonNull(this.accountRepository, "Account repository is required");
        requireNonNull(this.accountFactory, "Account factory is required");
        requireNonNull(this.existingAccountDTOAssembler, "Existing account DTO assembler is required");
    }

    @Override
    public String openAccount(NewAccountDTO newAccountDTO) {
        AccountId accountId = accountRepository.nextId();
        Account newAccount = accountFactory.create(
                accountId.toString(),
                newAccountDTO.getFirstName(), newAccountDTO.getLastName(),
                newAccountDTO.getAddress1(), newAccountDTO.getAddress2(), newAccountDTO.getCity(),
                newAccountDTO.getCounty(), newAccountDTO.getPostcode(),
                newAccountDTO.getContactNumber(), newAccountDTO.getEmail());
        accountRepository.add(newAccount);

        return accountId.toString();
    }

    @Override
    public List<ExistingAccountDTO> fetchAllAccounts() {
        List<Account> allAccounts = accountRepository.getAll();
        return existingAccountDTOAssembler.toDTOList(allAccounts);
    }

    @Override
    public ExistingAccountDTO fetchAccount(String accountId) throws AccountNotFoundException {
        requireNonNull(accountId, "Account id is required");
        Account account = accountRepository.getById(new AccountId(accountId));
        return existingAccountDTOAssembler.toDTO(account);
    }
}

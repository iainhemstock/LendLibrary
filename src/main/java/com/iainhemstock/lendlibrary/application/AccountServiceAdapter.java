package com.iainhemstock.lendlibrary.application;

import com.iainhemstock.lendlibrary.application.dto.AccountDTO;

import java.util.List;

public interface AccountServiceAdapter {
    String openAccount(AccountDTO accountDTO);
    List<AccountDTO> fetchAllAccounts();
    AccountDTO fetchAccount(String accountId);
}

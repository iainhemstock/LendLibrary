package com.iainhemstock.lendlibrary.application;

import com.iainhemstock.lendlibrary.application.dto.ExistingAccountDTO;
import com.iainhemstock.lendlibrary.application.dto.NewAccountDTO;

import java.util.List;

public interface AccountServiceAdapter {
    String openAccount(NewAccountDTO newAccountDTO);
    List<ExistingAccountDTO> fetchAllAccounts();
    ExistingAccountDTO fetchAccount(String accountId);
}

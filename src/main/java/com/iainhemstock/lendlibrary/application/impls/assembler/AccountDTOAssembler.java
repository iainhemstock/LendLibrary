package com.iainhemstock.lendlibrary.application.impls.assembler;

import com.iainhemstock.lendlibrary.application.dto.AccountDTO;
import com.iainhemstock.lendlibrary.domain.model.accounts.Account;
import com.iainhemstock.lendlibrary.domain.model.accounts.PersonProfile;

import java.util.ArrayList;
import java.util.List;

public class AccountDTOAssembler {

    public List<AccountDTO> toDTOList(List<Account> accounts) {
        List<AccountDTO> dtos = new ArrayList<>();

        for (Account account : accounts) {
            PersonProfile personProfile = account.getPersonProfile();

            final AccountDTO dto = new AccountDTO(
                    account.getId().toString(),
                    personProfile.getFullName().getFirstName().toString(),
                    personProfile.getFullName().getLastName().toString(),
                    personProfile.getAddress().getAddress1().toString(),
                    personProfile.getAddress().getAddress2().toString(),
                    personProfile.getAddress().getCity().toString(),
                    personProfile.getAddress().getCounty().toString(),
                    personProfile.getAddress().getPostcode().toString(),
                    personProfile.getContactDetails().getTelephone().toString(),
                    personProfile.getContactDetails().getEmail().toString());
            dtos.add(dto);
        }
        return dtos;
    }

    public AccountDTO toDTO(Account account) {
        return toDTOList(List.of(account)).get(0);
    }
}

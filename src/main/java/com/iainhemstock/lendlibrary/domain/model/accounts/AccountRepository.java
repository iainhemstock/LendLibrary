package com.iainhemstock.lendlibrary.domain.model.accounts;

import com.iainhemstock.lendlibrary.infrastructure.persistence.Repository;

public abstract class AccountRepository extends Repository<Account, AccountId> {

    @Override
    public AccountId nextId() {
        return new AccountId(super.generateUniqueId());
    }

}

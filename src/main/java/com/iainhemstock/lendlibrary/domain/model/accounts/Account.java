package com.iainhemstock.lendlibrary.domain.model.accounts;

public final class Account {
    private final AccountId accountId;
    private final PersonProfile personProfile;

    public Account(final AccountId accountId, final PersonProfile personProfile) {
        this.accountId = accountId;
        this.personProfile = personProfile;
    }

    public AccountId getAccountId() {
        return this.accountId;
    }
}

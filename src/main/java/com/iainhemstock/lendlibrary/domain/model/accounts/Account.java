package com.iainhemstock.lendlibrary.domain.model.accounts;

import com.iainhemstock.lendlibrary.domain.shared.Entity;

import java.util.Objects;

public final class Account implements Entity<AccountId> {

    private final AccountId accountId;
    private final PersonProfile personProfile;

    public Account(final AccountId accountId, final PersonProfile personProfile) {
        this.accountId = accountId;
        this.personProfile = personProfile;
    }

    @Override
    public AccountId getId() {
        return this.accountId;
    }

    public PersonProfile getPersonProfile() {
        return new PersonProfile(personProfile);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(accountId, account.accountId) &&
                Objects.equals(personProfile, account.personProfile);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, personProfile);
    }
}

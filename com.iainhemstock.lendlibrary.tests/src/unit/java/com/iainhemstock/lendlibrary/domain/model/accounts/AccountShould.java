package com.iainhemstock.lendlibrary.domain.model.accounts;

import com.iainhemstock.lendlibrary.domain.model.accounts.Account;
import com.iainhemstock.lendlibrary.domain.model.accounts.PersonProfile;
import com.iainhemstock.lendlibrary.domain.model.accounts.AccountId;
import org.junit.Test;
import org.mockito.Mockito;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public final class AccountShould {

    @Test
    public void return_its_id() {
        AccountId accountId = Mockito.mock(AccountId.class);
        PersonProfile personProfile = Mockito.mock(PersonProfile.class);
        Account account = new Account(accountId, personProfile);
        assertThat(account.getAccountId(), is(equalTo(accountId)));
    }
}

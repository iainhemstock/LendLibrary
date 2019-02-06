package com.iainhemstock.lendlibrary.domain.model.accounts;

import com.iainhemstock.lendlibrary.domain.shared.BaseId;
import com.iainhemstock.lendlibrary.domain.shared.BaseIdShould;

public final class AccountIdShould extends BaseIdShould {

    @Override
    protected BaseId getId(final String stringId) {
        return new AccountId(stringId);
    }

}

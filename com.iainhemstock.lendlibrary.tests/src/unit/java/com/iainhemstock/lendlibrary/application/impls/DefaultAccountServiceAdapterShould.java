package com.iainhemstock.lendlibrary.application.impls;

import com.iainhemstock.lendlibrary.application.AccountServiceAdapter;
import com.iainhemstock.lendlibrary.application.AccountServiceAdapterShould;

public final class DefaultAccountServiceAdapterShould extends AccountServiceAdapterShould {
    @Override
    protected AccountServiceAdapter getAccountServiceAdapter() {
        return new DefaultAccountServiceAdapter(super.accountRepository, super.accountFactory,
                super.existingAccountDTOAssembler);
    }

    @Override
    protected AccountServiceAdapter getAccountServiceAdapterWithNullFactory() {
        return new DefaultAccountServiceAdapter(super.accountRepository, null,
                super.existingAccountDTOAssembler);
    }

    @Override
    protected AccountServiceAdapter getAccountServiceAdapterWithNullRepository() {
        return new DefaultAccountServiceAdapter(null, super.accountFactory,
                super.existingAccountDTOAssembler);
    }

    @Override
    protected AccountServiceAdapter getAccountServiceAdapterWithNullAssembler() {
        return new DefaultAccountServiceAdapter(super.accountRepository, super.accountFactory,
                null);
    }
}

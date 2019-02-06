package com.iainhemstock.lendlibrary.domain.model.catalogue;

import com.iainhemstock.lendlibrary.domain.shared.BaseId;
import com.iainhemstock.lendlibrary.domain.shared.BaseIdShould;

public final class BookIdShould extends BaseIdShould {

    @Override
    protected BaseId getId(final String stringId) {
        return new BookId(stringId);
    }
}

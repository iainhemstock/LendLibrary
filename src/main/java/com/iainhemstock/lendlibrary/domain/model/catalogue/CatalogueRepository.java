package com.iainhemstock.lendlibrary.domain.model.catalogue;

import com.iainhemstock.lendlibrary.infrastructure.persistence.Repository;

public abstract class CatalogueRepository extends Repository<Book, BookId> {

    @Override
    public BookId nextId() {
        return new BookId(super.generateUniqueId());
    }

    public abstract int size();
}

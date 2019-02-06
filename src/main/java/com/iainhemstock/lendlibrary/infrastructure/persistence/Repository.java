package com.iainhemstock.lendlibrary.infrastructure.persistence;

import com.iainhemstock.lendlibrary.domain.shared.BaseId;
import com.iainhemstock.lendlibrary.domain.shared.Entity;

import java.util.List;
import java.util.UUID;

public abstract class Repository<
        Type extends Entity,
        TypeId extends BaseId> {

    public abstract TypeId nextId();

    public abstract boolean contains(Type type);

    public abstract void add(Type type);

    public abstract List<Type> getAll();

    public abstract Type getById(TypeId typeId);

    protected String generateUniqueId() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}

package com.iainhemstock.lendlibrary.domain.shared;

import java.util.List;
import java.util.UUID;

public abstract class Repository<Type extends Entity> {

    public abstract boolean contains(Type type);

    public abstract void add(Type type);

    public abstract void remove(Type type);

    public abstract List<Type> getAll();

    public abstract void update(Type type);

    protected String generateUniqueId() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}

package com.iainhemstock.lendlibrary.infrastructure.persistence;

import org.junit.Test;

public abstract class RepositoryShould {

    @Test
    public abstract void return_false_when_repo_does_not_contain_item();

    @Test
    public abstract void return_true_when_repo_contains_item();

    @Test
    public abstract void retrieve_all_items_in_repo();

    @Test
    public abstract void remove_item_from_repo();
}

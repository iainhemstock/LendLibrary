package com.iainhemstock.lendlibrary.infrastructure.persistence;

import com.iainhemstock.lendlibrary.domain.shared.Entity;
import com.iainhemstock.lendlibrary.domain.shared.Repository;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertFalse;

public abstract class RepositoryShould<
        RepositoryType extends Repository<RepositoryItem>,
        RepositoryItem extends Entity> {

    protected abstract RepositoryType getRepository();
    protected abstract RepositoryItem makeRepositoryItem();

    @Test
    public void return_false_when_repo_does_not_contain_item() {
        assertThat(getRepository().contains(makeRepositoryItem()),
                is(equalTo(false)));
    }

    @Test
    public void return_true_when_repo_contains_item() {
        RepositoryItem repositoryItem = makeRepositoryItem();
        getRepository().add(repositoryItem);

        assertThat(getRepository().contains(repositoryItem),
                is(equalTo(true)));
    }

    @Test
    public void retrieve_all_items_in_repo() {
        RepositoryItem item1 = makeRepositoryItem();
        RepositoryItem item2 = makeRepositoryItem();
        RepositoryItem item3 = makeRepositoryItem();

        getRepository().add(item1);
        getRepository().add(item2);
        getRepository().add(item3);

        assertThat(getRepository().getAll(),
                is(equalTo(List.of(item1, item2, item3))));
    }

    @Test
    public void remove_item_from_repo() {
        RepositoryItem item1 = makeRepositoryItem();
        RepositoryItem item2 = makeRepositoryItem();
        RepositoryItem item3 = makeRepositoryItem();

        getRepository().add(item1);
        getRepository().add(item2);
        getRepository().add(item3);

        getRepository().remove(item2);

        assertTrue(getRepository().contains(item1));
        assertFalse(getRepository().contains(item2));
        assertTrue(getRepository().contains(item3));
    }
}

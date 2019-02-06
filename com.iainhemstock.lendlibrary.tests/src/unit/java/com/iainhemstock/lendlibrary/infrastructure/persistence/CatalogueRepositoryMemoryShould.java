package com.iainhemstock.lendlibrary.infrastructure.persistence;

import com.iainhemstock.lendlibrary.domain.model.catalogue.CatalogueRepository;
import org.junit.Before;

public class CatalogueRepositoryMemoryShould extends CatalogueRepositoryShould {

    private CatalogueRepository catalogueRepository;

    @Before
    public void setUp() {
        catalogueRepository = new CatalogueRepositoryMemory();
    }

    @Override
    protected CatalogueRepository getRepository() {
        return catalogueRepository;
    }
}

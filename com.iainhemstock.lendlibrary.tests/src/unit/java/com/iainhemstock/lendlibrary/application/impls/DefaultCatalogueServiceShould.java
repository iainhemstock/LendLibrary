package com.iainhemstock.lendlibrary.application.impls;

import com.iainhemstock.lendlibrary.application.CatalogueService;
import com.iainhemstock.lendlibrary.application.CatalogueServiceShould;
import com.iainhemstock.lendlibrary.domain.model.catalogue.CatalogueRepository;

public class DefaultCatalogueServiceShould extends CatalogueServiceShould {

    @Override
    protected CatalogueService getCatalogueService(CatalogueRepository catalogueRepository) {
        return new DefaultCatalogueService(catalogueRepository);
    }
}

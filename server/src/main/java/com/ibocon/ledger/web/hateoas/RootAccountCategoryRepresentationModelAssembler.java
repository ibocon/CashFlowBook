package com.ibocon.ledger.web.hateoas;

import com.ibocon.ledger.repository.account.RootAccountCategory;
import com.ibocon.ledger.web.controller.RootAccountCategoryController;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

public class RootAccountCategoryRepresentationModelAssembler extends
        RepresentationModelAssemblerSupport<RootAccountCategory, RootAccountCategoryRepresentationModel> {

    public RootAccountCategoryRepresentationModelAssembler() {
        super(RootAccountCategoryController.class, RootAccountCategoryRepresentationModel.class);
    }

    @Override
    public RootAccountCategoryRepresentationModel toModel(RootAccountCategory rootAccountCategory) {
        return new RootAccountCategoryRepresentationModel(rootAccountCategory);
    }

    @Override
    protected RootAccountCategoryRepresentationModel instantiateModel(RootAccountCategory rootAccountCategory) {
        return createModelWithId(rootAccountCategory.getId(), rootAccountCategory);
    }
}

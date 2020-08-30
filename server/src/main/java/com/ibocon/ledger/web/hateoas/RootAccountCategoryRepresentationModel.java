package com.ibocon.ledger.web.hateoas;

import com.ibocon.ledger.repository.account.AccountingStandard;
import com.ibocon.ledger.repository.account.RootAccountCategory;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(value="root", collectionRelation="roots")
public class RootAccountCategoryRepresentationModel extends RepresentationModel<RootAccountCategoryRepresentationModel> {

    @Getter
    private AccountingStandard standard;

    @Getter
    private TranslatedStringRepresentationModel document;

    @Getter
    private boolean isDebit;

    @Getter
    private TranslatedStringRepresentationModel name;

    public RootAccountCategoryRepresentationModel(RootAccountCategory rootAccountCategory) {
        this.standard = rootAccountCategory.getStandard();
        this.document = new TranslatedStringRepresentationModel(rootAccountCategory.getDocument());
        this.isDebit = rootAccountCategory.isDebit();
        this.name = new TranslatedStringRepresentationModel(rootAccountCategory.getName());
    }
}

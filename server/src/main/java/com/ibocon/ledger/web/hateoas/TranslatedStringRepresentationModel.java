package com.ibocon.ledger.web.hateoas;

import com.ibocon.ledger.repository.TranslatedString;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(value = "translate", collectionRelation = "translates")
public class TranslatedStringRepresentationModel extends RepresentationModel<TranslatedStringRepresentationModel> {

    @Getter
    private String korean;

    @Getter
    private String english;

    public TranslatedStringRepresentationModel(TranslatedString translatedString) {
        this.korean = translatedString.getKorean();
        this.english = translatedString.getEnglish();
    }
}

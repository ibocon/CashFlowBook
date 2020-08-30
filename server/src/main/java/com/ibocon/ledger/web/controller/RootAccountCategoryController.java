package com.ibocon.ledger.web.controller;

import com.ibocon.ledger.repository.TranslatedString;
import com.ibocon.ledger.repository.TranslatedStringRepository;
import com.ibocon.ledger.repository.account.RootAccountCategory;
import com.ibocon.ledger.repository.account.RootAccountCategoryRepository;
import com.ibocon.ledger.web.hateoas.RootAccountCategoryRepresentationModel;
import com.ibocon.ledger.web.hateoas.RootAccountCategoryRepresentationModelAssembler;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path="/root-account-category", produces = "application/hal+json")
public class RootAccountCategoryController {

    private final TranslatedStringRepository translatedStringRepository;
    private final RootAccountCategoryRepository rootAccountCategoryRepository;

    @GetMapping(path="/financial-statement")
    public ResponseEntity<?> getFinancialStatement() {
        var rootAccountCategoryRepresentationModels = getRootAccountCategoryRepresentationModels("재무상태표");
        return new ResponseEntity<>(rootAccountCategoryRepresentationModels, HttpStatus.OK);
    }

    @GetMapping(path="/income-statement")
    public ResponseEntity<?> getIncomeStatement() {
        var rootAccountCategoryRepresentationModels = getRootAccountCategoryRepresentationModels("손익계산서");
        return new ResponseEntity<>(rootAccountCategoryRepresentationModels, HttpStatus.OK);
    }

    private CollectionModel<RootAccountCategoryRepresentationModel> getRootAccountCategoryRepresentationModels(String documentString) {
        TranslatedString documentTranslatedString = translatedStringRepository.findByKorean(documentString);
        List<RootAccountCategory> rootAccountCategories = rootAccountCategoryRepository.findByDocument(documentTranslatedString);
        return new RootAccountCategoryRepresentationModelAssembler().toCollectionModel(rootAccountCategories);
    }
}

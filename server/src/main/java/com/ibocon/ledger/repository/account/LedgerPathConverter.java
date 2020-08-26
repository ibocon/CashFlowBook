package com.ibocon.ledger.repository.account;

import javax.persistence.AttributeConverter;

public class LedgerPathConverter implements AttributeConverter<LedgerPath, String> {
    private static final String SEPARATOR = "/";

    @Override
    public String convertToDatabaseColumn(LedgerPath attribute) {
        return null;
    }

    @Override
    public LedgerPath convertToEntityAttribute(String dbData) {
        return null;
    }
}

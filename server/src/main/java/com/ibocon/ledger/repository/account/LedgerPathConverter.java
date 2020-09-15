package com.ibocon.ledger.repository.account;

import com.ibocon.ledger.repository.exception.LedgerPathException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

// /{rootAccountCategoryId}#/{AccountCategoryId}/{AccountCategoryId}...#/{AccountId}/{AccountId}...

@Slf4j
@Converter(autoApply = true)
public class LedgerPathConverter implements AttributeConverter<LedgerPath, String> {

    @Override
    public String convertToDatabaseColumn(LedgerPath attribute) {
        return attribute.toString();
    }

    @Override
    public LedgerPath convertToEntityAttribute(String dbData) {
        try {
            log.debug("Convert : " + dbData);
            return new LedgerPath(dbData);
        } catch(LedgerPathException exception) {
            log.error( "DB에 저장된 LedgerPath 를 객체로 전환하다 실패했습니다.", exception);
            return null;
        }
    }
}

package com.ibocon.ledger.repository.account;

import com.ibocon.ledger.repository.exception.LedgerPathException;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;



@Slf4j
@Getter
public class LedgerPath {

    // /{rootAccountCategoryId}#/{AccountCategoryId}/{AccountCategoryId}...#/{AccountId}/{AccountId}...

    private static final String HIGH_SEPARATOR = "#";
    private static final Pattern highPattern = Pattern.compile(HIGH_SEPARATOR);
    private static final String LOW_SEPARATOR = "/";
    private static final Pattern lowPattern = Pattern.compile(LOW_SEPARATOR);

    private Long rootAccountCategoryId;
    private List<Long> accountCategoryIds;
    private List<Long> accountIds;

    @Builder
    public LedgerPath(@NotNull Long rootAccountCategoryId, List<Long> accountCategoryIds, List<Long> accountIds) {
        this.rootAccountCategoryId = rootAccountCategoryId;
        this.accountCategoryIds = accountCategoryIds;
        this.accountIds = accountIds;
    }

    public LedgerPath(String path) throws LedgerPathException {
        var builder = LedgerPath.builder();

        var highPaths = highPattern.split(path);
        if(highPaths.length < 1) {
            throw new LedgerPathException("'" + path + "'은 약속된 경로 규칙에 위배됩니다.");
        }

        // RootAccountCategory 경로 해석
        if(highPaths.length > 1) {
            this.rootAccountCategoryId = getIdFromHighPath(highPaths[0]);
        }

        // AccountCategory 경로 해석
        List<Long> accountCategoryIds = new ArrayList<Long>();
        if(highPaths.length > 2) {
            accountCategoryIds = getIdsFromHighPath(highPaths[1]);
        }
        this.accountCategoryIds = accountCategoryIds;

        // Account 경로 해석
        List<Long> accountIds = new ArrayList<Long>();
        if(highPaths.length > 3) {
            accountIds = getIdsFromHighPath(highPaths[2]);
        }
        this.accountIds =accountIds;
    }

    @Override
    public String toString() {
        try {
            var ledgerPath = new StringBuilder(LOW_SEPARATOR);

            // root account category 를 경로에 추가합니다.
            if(rootAccountCategoryId != null) {
                ledgerPath.append(rootAccountCategoryId);
            } else {
                throw new LedgerPathException("Root account category 가 null 일 수 없습니다.");
            }

            // account category 를 경로에 추가합니다.
            if(!accountCategoryIds.isEmpty()) {
                appendHighPath(ledgerPath, accountCategoryIds);
            }

            // account 를 경로에 추가합니다.
            if (!accountIds.isEmpty()) {
                if(accountCategoryIds.isEmpty()) {
                    throw new LedgerPathException("account category 없이 account 만 존재하는 경로는 없습니다.");
                }
                appendHighPath(ledgerPath, accountIds);
            }

            return ledgerPath.toString();
        } catch(LedgerPathException exception) {
            log.error("LedgerPath 를 String 으로 변환하지 못했습니다.", exception);
            return "";
        }
    }

    private void appendHighPath(StringBuilder builder, List<Long> lowPaths) {
        builder.append(HIGH_SEPARATOR);
        for(var id : accountIds) {
            builder.append(LOW_SEPARATOR + id);
        }
    }

    private Long getIdFromHighPath(String highPath) throws LedgerPathException {
        var lowPaths = splitHighPathToLowPaths(highPath);

        if(lowPaths.length > 1)
            throw new LedgerPathException("'" + highPath + "'은 잘못된 경로 형식입니다.");

        return getIdFromLowPath(lowPaths[0]);
    }

    private List<Long> getIdsFromHighPath(String highPath) throws LedgerPathException {
        var lowPaths = lowPattern.split(highPath);

        var ids = new ArrayList<Long>();
        for(var lowPath : lowPaths) {
            ids.add(getIdFromLowPath(lowPath));
        }
        return ids;
    }

    private String[] splitHighPathToLowPaths(String highPath) throws LedgerPathException {
        var lowPaths = lowPattern.split(highPath);

        if(lowPaths.length < 1) {
            throw new LedgerPathException("'" + highPath + "'은 약속된 경로 형식에 맞지 않습니다.");
        }

        return lowPaths;
    }

    private Long getIdFromLowPath(String lowPath) throws LedgerPathException {
        try {
            return Long.parseLong(lowPath);
        } catch (NumberFormatException exception) {
            throw new LedgerPathException("'" + lowPath + "' 은 ID 형식의 경로가 아니므로 해석할 수 없습니다.", exception);
        }
    }
}

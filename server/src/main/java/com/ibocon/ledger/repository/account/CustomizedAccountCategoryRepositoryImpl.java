package com.ibocon.ledger.repository.account;


import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@RequiredArgsConstructor
public class CustomizedAccountCategoryRepositoryImpl implements CustomizedAccountCategoryRepository {

    private final EntityManager entityManager;

    @Override
    public List<AccountCategory> findByPathStartingWith(LedgerPath path) {

        path.setIsQuery(true);
        TypedQuery<AccountCategory> query =
                entityManager.createQuery("SELECT ac FROM AccountCategory ac WHERE path LIKE :path",
                        AccountCategory.class);
        query.setParameter("path", path);
        List<AccountCategory> subAccountCategories = query.getResultList();
        return  subAccountCategories;
    }
}

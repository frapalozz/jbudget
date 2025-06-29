package it.unicam.cs.mpgc.jbudget125914.models.services;

import it.unicam.cs.mpgc.jbudget125914.models.services.util.CriteriaQueryHelper;
import it.unicam.cs.mpgc.jbudget125914.models.services.util.TransactionUtil;

import java.time.temporal.Temporal;
import java.util.List;
import java.util.Set;

public class TransactionService<T> extends AbstractService<T> {

    public TransactionService(Class<T> transactionClass) {
        super(transactionClass);
    }

    public <D extends Temporal & Comparable<D>> List<T> findAll(D from, D to) {
        return findAll(from, to, null);
    }

    public <A, D extends Temporal & Comparable<D>> List<T> findAll(D from, D to, A account) {
        return findAll(from, to, account, null);
    }

    public <A, C, D extends Temporal & Comparable<D>> List<T> findAll(D from, D to, A account, Set<C> tags) {

        return TransactionUtil.executeInTransactionReturn(em -> {
            CriteriaQueryHelper<T> helper = new CriteriaQueryHelper<>(em, this.getEntityClass());

            helper.where(helper.getCb().greaterThanOrEqualTo(helper.getRoot().get("date"), from));
            helper.where(helper.getCb().lessThanOrEqualTo(helper.getRoot().get("date"), to));

            if (account != null) {
                helper.where(helper.getCb().equal(helper.getRoot().get("account"), account));
            }
            if (tags != null) {
                helper.where(helper.getRoot().get("tags").in(tags));
            }

            return helper.getResultList();
        });
    }
}

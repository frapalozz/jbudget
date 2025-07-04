package it.unicam.cs.mpgc.jbudget125914.models.services;

import it.unicam.cs.mpgc.jbudget125914.models.services.util.CriteriaQueryHelper;
import it.unicam.cs.mpgc.jbudget125914.models.services.util.TransactionUtil;
import jakarta.persistence.criteria.Expression;

public class AccountService<
        A, N extends Number, G
        > extends AbstractService<A> {

    public AccountService(Class<A> entityClass) {
        super(entityClass);
    }

    public N accountsInitialBalance(Class<N> amountClass, Class<N> currencyClass, G group) {
        return TransactionUtil.executeInTransactionReturn(em -> {
            CriteriaQueryHelper<A, N> helper = new CriteriaQueryHelper<>(em, getEntityClass(), currencyClass);

            helper.where(helper.getRoot().get("groupId").equalTo(group));
            Expression<N> accountsBalance = helper.getCb().sum(
                    helper.getRoot().get("initialAmount").get("amount")
            );

            helper.getCq().select(accountsBalance);

            return helper.convertor(amountClass, currencyClass);
        });
    }
}

package it.unicam.cs.mpgc.jbudget125914.models.services;

import it.unicam.cs.mpgc.jbudget125914.models.entities.account.Account;
import it.unicam.cs.mpgc.jbudget125914.models.embeddable.amount.Amount;
import it.unicam.cs.mpgc.jbudget125914.models.entities.group.Group;
import it.unicam.cs.mpgc.jbudget125914.models.services.util.CriteriaQueryHelper;
import it.unicam.cs.mpgc.jbudget125914.models.services.util.TransactionUtil;
import jakarta.persistence.criteria.Expression;

import java.util.Set;

public class AccountService<
        A extends Account<N>,
        NU extends Number,
        N extends Amount<NU, N>,
        G extends Group<?, ?, ?, A>
        > extends AbstractService<A> {

    public AccountService(Class<A> entityClass) {
        super(entityClass);
    }

    public N accountsInitialBalance(Class<N> amountClass, Class<NU> currencyClass, G group) {
        return TransactionUtil.executeInTransactionReturn(em -> {
            CriteriaQueryHelper<A, NU> helper = new CriteriaQueryHelper<>(em, getEntityClass(), currencyClass);

            helper.where(helper.getRoot().get("groupId").equalTo(group));
            Expression<NU> accountsBalance = helper.getCb().sum(
                    helper.getRoot().get("initialAmount").get("amount")
            );

            helper.getCq().select(accountsBalance);

            return helper.convertor(amountClass, currencyClass);
        });
    }
}

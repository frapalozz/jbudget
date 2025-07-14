package it.unicam.cs.mpgc.jbudget125914.models.repository.transaction;

import it.unicam.cs.mpgc.jbudget125914.service.filterService.FinancialFilterService;
import it.unicam.cs.mpgc.jbudget125914.models.repository.AbstractRepository;
import it.unicam.cs.mpgc.jbudget125914.models.repository.util.CriteriaQueryHelper;
import it.unicam.cs.mpgc.jbudget125914.models.repository.util.TransactionUtil;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractFinancialTransactionRepository<T> extends AbstractRepository<T> implements TransactionRepository<T, FinancialFilterService> {
    /**
     * Construct a new AbstractDAO
     *
     * @param entityClass the entity for the DAO
     * @throws NullPointerException if {@code entityClass} is null
     */
    public AbstractFinancialTransactionRepository(@NonNull Class<T> entityClass) {
        super(entityClass);
    }


    @Override
    public List<T> findAll(@NonNull FinancialFilterService filter) {

        if(filter.getStartDate().isAfter(filter.getEndDate())) return new ArrayList<>();

        return TransactionUtil.executeInTransactionReturn(em -> {
            CriteriaQueryHelper<T, T> helper = new CriteriaQueryHelper<>(em, this.getEntityClass(), this.getEntityClass());

            helper.where(helper.getRoot().get("account").get("groupId").equalTo(filter.getGroup()));
            helper.where(helper.getCb().between(helper.getRoot().get("date"), filter.getStartDate(), filter.getEndDate()));
            helper.where(helper.in("account", filter.getAccounts()));
            helper.where(helper.containsAny("tags", filter.getTags()));

            return List.copyOf(helper.getResultList());
        });
    }
}

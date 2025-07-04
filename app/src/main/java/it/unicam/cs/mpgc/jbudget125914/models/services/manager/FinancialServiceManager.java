package it.unicam.cs.mpgc.jbudget125914.models.services.manager;

import it.unicam.cs.mpgc.jbudget125914.models.embeddable.amount.FinancialAmount;
import it.unicam.cs.mpgc.jbudget125914.models.entities.account.FinancialAccount;
import it.unicam.cs.mpgc.jbudget125914.models.entities.category.FinancialCategory;
import it.unicam.cs.mpgc.jbudget125914.models.entities.group.FinancialGroup;
import it.unicam.cs.mpgc.jbudget125914.models.entities.tag.FinancialTag;
import it.unicam.cs.mpgc.jbudget125914.models.entities.transaction.FinancialTransaction;
import it.unicam.cs.mpgc.jbudget125914.models.services.*;
import it.unicam.cs.mpgc.jbudget125914.models.services.manager.fetchManager.FinancialFetchManager;
import it.unicam.cs.mpgc.jbudget125914.models.services.manager.filterManager.FinancialFilterManager;
import it.unicam.cs.mpgc.jbudget125914.models.services.manager.generalManager.FinancialGeneralManager;

import java.math.BigDecimal;
import java.time.LocalDate;

public class FinancialServiceManager extends AbstractServiceManager<
        FinancialTransaction,
        FinancialAccount,
        FinancialTag,
        BigDecimal,
        LocalDate,
        FinancialAmount,
        FinancialCategory,
        FinancialGroup,
        FinancialGeneralManager,
        FinancialFilterManager,
        FinancialFetchManager> {

    private FinancialServiceManager() {
        setFetchManager(new FinancialFetchManager());
        setFilterManager(new FinancialFilterManager());
        setGeneralManager(new FinancialGeneralManager());
        getGeneralManager().setTransactionService(new TransactionService<>(FinancialTransaction.class));
        getGeneralManager().setAccountService(new AccountService<>(FinancialAccount.class));
        getGeneralManager().setCategoryService(new CategoryService<>(FinancialCategory.class));
        getGeneralManager().setGroupService(new GroupService<>(FinancialGroup.class));
        getGeneralManager().setTagService(new TagService<>(FinancialTag.class));
    }

    private static FinancialServiceManager instance = new FinancialServiceManager();

    public static FinancialServiceManager getInstance() {
        if(instance == null) {
            instance = new FinancialServiceManager();
        }
        return instance;
    }

    @Override
    public void update() {
        getFetchManager().update(getGeneralManager(), getFilterManager(), this::reflectUpdate);
    }

    private void reflectUpdate() {
        getChanges().set(!getChanges().getValue());
    }
}

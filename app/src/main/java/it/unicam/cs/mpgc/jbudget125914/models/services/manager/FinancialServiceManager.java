package it.unicam.cs.mpgc.jbudget125914.models.services.manager;

import it.unicam.cs.mpgc.jbudget125914.models.embeddable.amount.FinancialAmount;
import it.unicam.cs.mpgc.jbudget125914.models.entities.account.FinancialAccount;
import it.unicam.cs.mpgc.jbudget125914.models.entities.category.FinancialCategory;
import it.unicam.cs.mpgc.jbudget125914.models.entities.group.FinancialGroup;
import it.unicam.cs.mpgc.jbudget125914.models.entities.tag.FinancialTag;
import it.unicam.cs.mpgc.jbudget125914.models.entities.transaction.FinancialTransaction;
import it.unicam.cs.mpgc.jbudget125914.models.services.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class FinancialServiceManager extends AbstractServiceManager<
        FinancialTransaction,
        FinancialAccount,
        FinancialTag,
        BigDecimal,
        LocalDate,
        FinancialAmount,
        FinancialCategory,
        FinancialGroup> {

    protected FinancialServiceManager() {
        setTransactionService(new TransactionService<>(FinancialTransaction.class));
        setAccountService(new AccountService<>(FinancialAccount.class));
        setCategoryService(new CategoryService<>(FinancialCategory.class));
        setGroupService(new GroupService<>(FinancialGroup.class));
        setTagService(new TagService<>(FinancialTag.class));
    }

    private static FinancialServiceManager instance = new FinancialServiceManager();

    public static FinancialServiceManager getInstance() {
        if(instance == null) {
            instance = new FinancialServiceManager();
        }
        return instance;
    }

    @Override
    public void setChanges(int changes) {
        if(changes > 1000)
            getChanges().set(0);
        else
            getChanges().set(changes);

    }

    @Override
    public Set<FinancialAccount> getGroupAccounts() {
        return getGroup().getAccounts();
    }

    @Override
    public Set<FinancialTag> getGroupTags() {
        return getGroup().getTags();
    }

    @Override
    public void update() {
        if(getGroup() == null) {
            return;
        }
        updateTransactions();
        updateCategoryBalance();
        setBalance();
        increaseChanges();
    }

    private void setBalance() {
        if(getGroup().getAccounts().isEmpty()) {
            setBalance(new FinancialAmount(BigDecimal.ZERO));
            return;
        }

        FinancialAmount amount = getTransactionService().getTransactionAmount(LocalDate.now(), FinancialAmount.class, BigDecimal.class, getGroup().getAccounts());
        setBalance(getGroup()
                .getAccounts()
                .stream()
                .map(FinancialAccount::getInitialAmount)
                .reduce(new FinancialAmount(BigDecimal.ZERO), FinancialAmount::add)
                .add(amount));
    }

    private void updateTransactions() {
        setTransactions(getTransactionService().findAll(
                getStartDate(),
                getEndDate(),
                getAccounts(),
                getTags(),
                getGroup()
        ));
    }

    private void updateCategoryBalance() {
        setCategoryBalance(getCategoryService().getCategoryBalance(
                FinancialTransaction.class,
                getStartDate(),
                getEndDate(),
                getAccounts(),
                getGroup()
        ));
    }

    private void increaseChanges() {
        setChanges(getChanges().intValue()+1);
    }
}

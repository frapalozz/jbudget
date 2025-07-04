package it.unicam.cs.mpgc.jbudget125914.models.services.manager.fetchManager;

import it.unicam.cs.mpgc.jbudget125914.models.embeddable.amount.FinancialAmount;
import it.unicam.cs.mpgc.jbudget125914.models.entities.account.FinancialAccount;
import it.unicam.cs.mpgc.jbudget125914.models.entities.category.FinancialCategory;
import it.unicam.cs.mpgc.jbudget125914.models.entities.group.FinancialGroup;
import it.unicam.cs.mpgc.jbudget125914.models.entities.tag.FinancialTag;
import it.unicam.cs.mpgc.jbudget125914.models.entities.transaction.FinancialSchedule;
import it.unicam.cs.mpgc.jbudget125914.models.entities.transaction.FinancialTransaction;
import it.unicam.cs.mpgc.jbudget125914.models.services.manager.Action;
import it.unicam.cs.mpgc.jbudget125914.models.services.manager.filterManager.FinancialFilterManager;
import it.unicam.cs.mpgc.jbudget125914.models.services.manager.generalManager.FinancialGeneralManager;
import javafx.application.Platform;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class FinancialFetchManager extends AbstractFetchManager<
        FinancialTransaction,
        FinancialSchedule,
        FinancialAmount,
        BigDecimal,
        FinancialAccount,
        LocalDate,
        FinancialTag,
        FinancialCategory,
        FinancialGroup,
        FinancialGeneralManager,
        FinancialFilterManager> {

    @Override
    public void update(FinancialGeneralManager generalManager, FinancialFilterManager filterManager, Action action) {
        List<CompletableFuture<Void>> futures = new ArrayList<>();

        if(filterManager.getGroup() != null) {
            futures.add(CompletableFuture.runAsync(() -> setBalance(generalManager, filterManager)));
            futures.add(CompletableFuture.runAsync(() -> updateTransactions(generalManager, filterManager)));
            futures.add(CompletableFuture.runAsync(() -> updateCategoryBalance(generalManager, filterManager)));
            futures.add(CompletableFuture.runAsync(() -> updateSchedules(generalManager, filterManager)));
        }
        futures.add(CompletableFuture.runAsync(() -> updateGroups(generalManager)));

        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                .thenRunAsync(action::execute, Platform::runLater)
                .exceptionally(ex -> {
                    Platform.runLater(() -> System.out.println("Error: " + ex.getMessage()));
                    return null;
                });
    }

    private void setBalance(FinancialGeneralManager generalManager, FinancialFilterManager filterManager) {

        if(filterManager.getGroup().getAccounts().isEmpty()) {
            setBalance(new FinancialAmount(BigDecimal.ZERO));
            return;
        }

        FinancialAmount amount = generalManager.getTransactionService().getTransactionAmount(LocalDate.now(), FinancialAmount.class, BigDecimal.class, filterManager.getGroup().getAccounts());
        setBalance(filterManager.getGroup()
                .getAccounts()
                .stream()
                .map(FinancialAccount::getInitialAmount)
                .reduce(new FinancialAmount(BigDecimal.ZERO), FinancialAmount::add)
                .add(amount));
    }

    private void updateTransactions(FinancialGeneralManager generalManager, FinancialFilterManager filterManager) {
        setTransactions(generalManager.getTransactionService().findAll(
                filterManager.getStartDate(),
                filterManager.getEndDate(),
                filterManager.getAccounts(),
                filterManager.getTags(),
                filterManager.getGroup()
        ));
    }

    private void updateSchedules(FinancialGeneralManager generalManager, FinancialFilterManager filterManager) {
        setSchedules(generalManager.getScheduleService().findAll(
                filterManager.getStartDate(),
                filterManager.getEndDate(),
                filterManager.getAccounts(),
                filterManager.getTags(),
                filterManager.getGroup()
        ));
    }

    private void updateCategoryBalance(FinancialGeneralManager generalManager, FinancialFilterManager filterManager) {
        setCategoryBalance(generalManager.getCategoryService().getCategoryBalance(
                FinancialTransaction.class,
                filterManager.getStartDate(),
                filterManager.getEndDate(),
                filterManager.getAccounts(),
                filterManager.getGroup()
        ));
    }

    private void updateGroups(FinancialGeneralManager generalManager) {
        setGroups(generalManager.getGroupService().findAll());
    }
}

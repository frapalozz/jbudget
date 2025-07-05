/**
 * MIT License
 * Copyright (c) 2025 Francesco Palozzi
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package it.unicam.cs.mpgc.jbudget125914.controller.manager.fetchManager;

import it.unicam.cs.mpgc.jbudget125914.models.embeddable.amount.FinancialAmount;
import it.unicam.cs.mpgc.jbudget125914.models.entities.account.FinancialAccount;
import it.unicam.cs.mpgc.jbudget125914.models.entities.category.FinancialCategory;
import it.unicam.cs.mpgc.jbudget125914.models.entities.group.FinancialGroup;
import it.unicam.cs.mpgc.jbudget125914.models.entities.tag.FinancialTag;
import it.unicam.cs.mpgc.jbudget125914.models.entities.transaction.FinancialSchedule;
import it.unicam.cs.mpgc.jbudget125914.models.entities.transaction.FinancialTransaction;
import it.unicam.cs.mpgc.jbudget125914.controller.manager.filterManager.FinancialFilterManager;
import it.unicam.cs.mpgc.jbudget125914.controller.manager.generalManager.FinancialGeneralManager;
import javafx.application.Platform;
import lombok.NonNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * This class represent a FinancialFetchManager
 */
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
    public void update(@NonNull FinancialGeneralManager generalManager, @NonNull FinancialFilterManager filterManager, Runnable action) {
        List<CompletableFuture<Void>> futures = new ArrayList<>();

        if(filterManager.getGroup() != null) {
            futures.add(CompletableFuture.runAsync(() -> setBalance(generalManager, filterManager)));
            futures.add(CompletableFuture.runAsync(() -> updateTransactions(generalManager, filterManager)));
            futures.add(CompletableFuture.runAsync(() -> updateSchedules(generalManager, filterManager)));
        }
        futures.add(CompletableFuture.runAsync(() -> updateGroups(generalManager)));

        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                .thenRun(() -> {
                    updateCategoryBalance(generalManager);
                    updateChartTransactions();
                })
                .thenRun(action)
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

        FinancialAmount amount = generalManager.getTransactionDAO().getTransactionAmount(LocalDate.now(), FinancialAmount.class, BigDecimal.class, filterManager.getGroup().getAccounts());
        setBalance(filterManager.getGroup()
                .getAccounts()
                .stream()
                .map(FinancialAccount::getInitialAmount)
                .reduce(new FinancialAmount(BigDecimal.ZERO), FinancialAmount::add)
                .add(amount));
    }

    private void updateTransactions(FinancialGeneralManager generalManager, FinancialFilterManager filterManager) {
        setTransactions(generalManager.getTransactionDAO().findAll(
                filterManager.getStartDate(),
                filterManager.getEndDate(),
                filterManager.getAccounts(),
                filterManager.getTags(),
                filterManager.getGroup()
        ));
    }

    private void updateSchedules(FinancialGeneralManager generalManager, FinancialFilterManager filterManager) {
        setSchedules(generalManager.getScheduleDAO().findAll(
                filterManager.getStartDate(),
                filterManager.getEndDate(),
                filterManager.getAccounts(),
                filterManager.getTags(),
                filterManager.getGroup()
        ));
    }

    private void updateCategoryBalance(FinancialGeneralManager generalManager) {
        setCategoryBalance(generalManager.getCategoryDAO().getCategoryBalance(getTransactions()));
    }

    private void updateGroups(FinancialGeneralManager generalManager) {
        setGroups(generalManager.getGroupDAO().findAll());
    }

    private void updateChartTransactions() {
        if(getTransactions() == null) return;
        setChartTransactions(new ArrayList<>());

        sumTransactions();
    }

    private void sumTransactions() {
        getTransactions().forEach(ft -> {

            FinancialTransaction chartTransaction = getChartTransactions().stream()
                    .filter(t -> t.getDate().equals(ft.getDate()) && t.getAmount().signum() == ft.getAmount().signum())
                    .findFirst()
                    .orElse(null);

            if(chartTransaction != null) {
                FinancialTransaction transaction = new FinancialTransaction();
                transaction.setAmount(chartTransaction.getAmount().add(ft.getAmount()));
                transaction.setDate(chartTransaction.getDate());
                getChartTransactions().remove(chartTransaction);
                getChartTransactions().add(transaction);
            } else {
                getChartTransactions().add(ft);
            }
        });
    }
}

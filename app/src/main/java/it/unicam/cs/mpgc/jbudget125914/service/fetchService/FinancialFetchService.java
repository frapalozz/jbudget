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

package it.unicam.cs.mpgc.jbudget125914.service.fetchService;

import it.unicam.cs.mpgc.jbudget125914.models.embeddable.amount.FinancialAmount;
import it.unicam.cs.mpgc.jbudget125914.models.entities.account.FinancialAccount;
import it.unicam.cs.mpgc.jbudget125914.models.entities.category.FinancialCategory;
import it.unicam.cs.mpgc.jbudget125914.models.entities.group.FinancialGroup;
import it.unicam.cs.mpgc.jbudget125914.models.entities.transaction.FinancialSchedule;
import it.unicam.cs.mpgc.jbudget125914.models.entities.transaction.FinancialTransaction;
import it.unicam.cs.mpgc.jbudget125914.service.filterService.FinancialFilterService;
import it.unicam.cs.mpgc.jbudget125914.service.repositoryService.FinancialRepositoryService;
import javafx.application.Platform;
import lombok.NonNull;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.CompletableFuture;

/**
 * This class represent a FinancialFetchManager
 */
public final class FinancialFetchService extends AbstractFetchService<
        FinancialTransaction,
        FinancialSchedule,
        FinancialAmount,
        FinancialCategory,
        FinancialGroup,
        FinancialRepositoryService,
        FinancialFilterService> {

    @Override
    public void update(@NonNull FinancialRepositoryService generalManager, @NonNull FinancialFilterService filterManager, Runnable action) {

        List<CompletableFuture<Void>> futures = new ArrayList<>();

        if(filterManager.getGroup() != null) {
            futures.add(CompletableFuture.runAsync(setBalance(generalManager, filterManager)));
            futures.add(CompletableFuture.runAsync(updateTransactions(generalManager, filterManager)));
            futures.add(CompletableFuture.runAsync(updateSchedules(generalManager, filterManager)));
        } else {
            setBalance(new FinancialAmount(BigDecimal.ZERO));
            setTransactions(new ArrayList<>());
            setSchedules(new ArrayList<>());
        }
        futures.add(CompletableFuture.runAsync(updateGroups(generalManager)));

        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                .thenRunAsync(() -> {
                    updateCategoryBalance(getTransactions());
                    updateChartTransactions();
                })
                .thenRun(action)
                .exceptionally(ex -> {
                    Platform.runLater(() -> System.out.println("Error: " + ex.getMessage()));
                    return null;
                });
    }

    @Override
    public void updateGroup(FinancialRepositoryService generalManager, FinancialFilterService filterManager) {
        filterManager.setGroup(generalManager.getGroupRepository().findById(filterManager.getGroup().getGroupId()));
    }

    private Runnable setBalance(FinancialRepositoryService generalManager, FinancialFilterService filterManager) {
        return () -> {
            if (filterManager.getGroup().getAccounts().isEmpty()) {
                setBalance(new FinancialAmount(BigDecimal.ZERO));
                return;
            }

            FinancialAmount amount = generalManager.getTransactionRepository().getBalance(filterManager);
            setBalance(filterManager.getGroup().getAccounts().stream()
                    .map(FinancialAccount::getInitialAmount)
                    .reduce(new FinancialAmount(BigDecimal.ZERO), FinancialAmount::add)
                    .add(amount)
            );
        };
    }

    private Runnable updateTransactions(FinancialRepositoryService generalManager, FinancialFilterService filterManager) {
        return () -> setTransactions(generalManager.getTransactionRepository().findAll(filterManager));
    }

    private Runnable updateSchedules(FinancialRepositoryService generalManager, FinancialFilterService filterManager) {
        return () -> setSchedules(generalManager.getScheduleRepository().findAll(filterManager));
    }

    private void updateCategoryBalance(List<FinancialTransaction> transactions) {
        setCategoryBalance(getCategoryBalance(transactions));
    }

    private Runnable updateGroups(FinancialRepositoryService generalManager) {
        return () -> setGroups(generalManager.getGroupRepository().findAll());
    }

    private void updateChartTransactions() {
        if(getTransactions() == null) return;
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

    private List<Map<FinancialCategory, FinancialAmount>> getCategoryBalance(List<FinancialTransaction> transactions) {
        if(transactions == null) return Collections.emptyList();
        List<Map<FinancialCategory, FinancialAmount>> categoryBalance = new ArrayList<>();
        categoryBalance.add(new HashMap<>());
        categoryBalance.add(new HashMap<>());

        transactions.forEach(t ->
                t.getTags().stream()
                        .findFirst()
                        .ifPresent(tag -> addTo(categoryBalance, tag.getCategory(), t.getAmount()))
        );

        return categoryBalance;
    }

    private void checkOrAdd(Map<FinancialCategory, FinancialAmount> map, FinancialCategory category, FinancialAmount amount) {
        if(!map.containsKey(category)) {
            map.put(category, amount);
        } else {
            map.put(category, map.get(category).add(amount));
        }
    }

    private void addTo(List<Map<FinancialCategory, FinancialAmount>> categoryBalance, FinancialCategory category, FinancialAmount amount) {
        if(amount.signum() > 0) {
            checkOrAdd(categoryBalance.getFirst(), category, amount);
        } else {
            checkOrAdd(categoryBalance.get(1), category, amount);
        }
    }
}

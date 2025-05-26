package it.unicam.cs.mpgc.jbudget125914.BudgetManager;

import it.unicam.cs.mpgc.jbudget125914.categories.Category;
import it.unicam.cs.mpgc.jbudget125914.currency.Currency;
import it.unicam.cs.mpgc.jbudget125914.manager.AccountManager;
import it.unicam.cs.mpgc.jbudget125914.manager.DateRange;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class DefaultBudgetManager<T extends Currency<T>> implements BalanceInterface<T>{

    private final Map<String, AccountManager<T>> accounts;
    private final Map<Category, Integer> categories;

    public DefaultBudgetManager(Map<String, AccountManager<T>> accounts, Map<Category, Integer> categories) {
        this.accounts = accounts;
        this.categories = categories;
    }

    @Override
    public T getBalance() {
        return accounts.values().stream().map(AccountManager::getBalance).reduce(Currency::sum).orElse(null);
    }

    @Override
    public Map<LocalDate, T> getBalanceMovement(DateRange dateRange) {
        Map<LocalDate, T> balanceMovement = new HashMap<>();

        accounts.values()
                .forEach(a -> a.getBalanceMovement(dateRange)
                        .forEach((k, v) -> addBalance(balanceMovement, k, v)));

        return balanceMovement;
    }

    private void addBalance(Map<LocalDate, T> balanceMovement, LocalDate date, T value) {
        if(balanceMovement.containsKey(date)) {
            balanceMovement.put(date, balanceMovement.get(date).sum(value));
        }
        else {
            balanceMovement.put(date, value);
        }
    }

    @Override
    public Map<LocalDate, T> getBalanceMovement(DateRange dateRange, String accountName) {
        return accounts.get(accountName).getBalanceMovement(dateRange);
    }

    @Override
    public T getBalance(LocalDate date) {
        return accounts
                .values()
                .stream()
                .map(a -> a.getBalanceAtDate(date))
                .reduce(Currency::sum)
                .orElse(null);
    }

    @Override
    public T getBalance(String accountName) {
        return accounts.get(accountName).getBalance();
    }

    @Override
    public T getBalance(LocalDate date, String accountName) {
        return accounts.get(accountName).getBalanceAtDate(date);
    }
}

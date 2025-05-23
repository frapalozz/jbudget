package it.unicam.cs.mpgc.jbudget125914;

import it.unicam.cs.mpgc.jbudget125914.categories.DefaultCategory;
import it.unicam.cs.mpgc.jbudget125914.manager.BudgetManager;
import it.unicam.cs.mpgc.jbudget125914.manager.DateRange;
import it.unicam.cs.mpgc.jbudget125914.transaction.Transaction;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AccountsManager implements Manager{

    private Map<String, BudgetManager> accounts;
    private Map<DefaultCategory, Integer> categoryMap;
    private DateRange dateRange;

    @Override
    public double getBalance() {
        return accounts.values().stream().mapToDouble(BudgetManager::getBalance).sum();
    }

    @Override
    public double getBalance(String account) {
        return accounts.get(account).getBalance();
    }

    @Override
    public List<BudgetManager> getAccounts() {
        return List.copyOf(accounts.values());
    }

    @Override
    public void addAccount(BudgetManager account, String name) {
        this.accounts.put(name, account);
    }

    @Override
    public void removeAccount(String name) {
        this.accounts.remove(name);
    }

    @Override
    public void setDateRange(DateRange dateRange) {
        this.dateRange = dateRange;
    }

    @Override
    public DateRange getDateRange() {
        return this.dateRange;
    }

    @Override
    public Set<DefaultCategory> getCategories() {
        return Set.copyOf(categoryMap.keySet());
    }

    @Override
    public void addCategory(DefaultCategory category) {
        this.categoryMap.put(category, 1);
    }

    @Override
    public void addCategory(DefaultCategory category, int priority) {
        this.categoryMap.put(category, priority);
    }

    @Override
    public void removeCategory(DefaultCategory category) {
        this.categoryMap.remove(category);
    }

    @Override
    public List<Transaction> getTransaction() {
        return getTransaction(null, null, null);
    }

    @Override
    public List<Transaction> getTransaction(String account) {
        return getTransaction(null, account, null);
    }

    @Override
    public List<Transaction> getTransaction(boolean isExpense) {
        return getTransaction(null, null, isExpense);
    }

    @Override
    public List<Transaction> getTransaction(String account, boolean isExpense) {
        return getTransaction(null, account, isExpense);
    }

    @Override
    public List<Transaction> getTransaction(DefaultCategory category) {
        return getTransaction(List.of(category));
    }

    @Override
    public List<Transaction> getTransaction(List<DefaultCategory> category) {
        return getTransaction(category, null, null);
    }

    @Override
    public List<Transaction> getTransaction(DefaultCategory category, boolean isExpense) {
        return getTransaction(List.of(category), isExpense);
    }

    @Override
    public List<Transaction> getTransaction(List<DefaultCategory> category, boolean isExpense) {
        return getTransaction(category, null, isExpense);
    }

    @Override
    public void addTransaction(Transaction transaction, String account) {
        this.accounts.get(account).addTransaction(transaction);
    }

    @Override
    public void removeTransaction(Transaction transaction, String account) {
        this.accounts.get(account).removeTransaction(transaction);
    }


    private List<Transaction> getTransaction(List<DefaultCategory> categories, String account, Boolean isExpense) {

        return filterDateRange(
                filterCategory(
                        categories, filterExpense(isExpense, filterAccount(account))
                )
        );
    }

    private Stream<Transaction> filterAccount(String account) {
        if(account != null) {
            return this.accounts.get(account).getTransactions().stream();
        }
        return accounts.values().stream().flatMap(t -> t.getTransactions().stream());
    }

    private Stream<Transaction> filterExpense(Boolean isExpense, Stream<Transaction> transactions) {
        if(isExpense != null) {
            return transactions.filter(t -> t.isExpense() == isExpense);
        }
        return transactions;
    }

    private Stream<Transaction> filterCategory(List<DefaultCategory> categories, Stream<Transaction> transactions) {
        if(categories != null) {
            return transactions = transactions.filter(t -> t.categories().containsAll(categories));
        }
        return transactions;
    }

    private List<Transaction> filterDateRange(Stream<Transaction> transactions) {
        return List.copyOf(transactions.filter(t -> t.date().isAfter(dateRange.startDate()) && t.date().isBefore(dateRange.endDate())).collect(Collectors.toList()));
    }
}

package it.unicam.cs.mpgc.jbudget125914;

import it.unicam.cs.mpgc.jbudget125914.categories.DefaultCategory;
import it.unicam.cs.mpgc.jbudget125914.manager.BudgetManager;
import it.unicam.cs.mpgc.jbudget125914.manager.DateRange;
import it.unicam.cs.mpgc.jbudget125914.transaction.Transaction;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class AccountsManager implements Manager{

    private Map<String, BudgetManager> accounts;
    private Map<DefaultCategory, Integer> categoryMap;

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
    public BudgetManager getAccount(String account) {
        return accounts.get(account);
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
    public void editCategoryPriority(DefaultCategory category, int priority) {
        this.categoryMap.put(category, priority);
    }

    @Override
    public void removeCategory(DefaultCategory category) {
        this.categoryMap.remove(category);
    }

    @Override
    public List<Transaction> getTransactions() {
        return filterTransactions(null, null, null, null);
    }

    @Override
    public List<Transaction> getTransaction(
            String account,
            Boolean isExpense,
            List<DefaultCategory> categories,
            DateRange dateRange) {
        return filterTransactions(account, isExpense, categories, dateRange);
    }

    @Override
    public void addTransaction(Transaction transaction, String account) {
        this.accounts.get(account).addTransaction(transaction);
    }

    @Override
    public void removeTransaction(Transaction transaction, String account) {
        this.accounts.get(account).removeTransaction(transaction);
    }


    private List<Transaction> filterTransactions(String account, Boolean isExpense, List<DefaultCategory> categories, DateRange dateRange) {

        List<BudgetManager> accountBudgetManagers = this.filterAccount(account);

        return accountBudgetManagers.stream()
                .flatMap(b -> b.getTransactions(categories, isExpense, dateRange).stream())
                .collect(Collectors.toList());
    }

    private List<BudgetManager> filterAccount(String account) {
        return (account != null)? List.of(getAccount(account)) : getAccounts();
    }
}

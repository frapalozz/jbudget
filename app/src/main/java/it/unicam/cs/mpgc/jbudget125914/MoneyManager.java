package it.unicam.cs.mpgc.jbudget125914;

import java.util.List;
import java.util.Set;

public class MoneyManager implements TransactionsManager{

    List<Transaction<Money, Category>> transactions;
    Set<Category> categories;

    public MoneyManager(List<Transaction<Money, Category>> transactions, Set<Category> categories) {
        this.transactions = transactions;
        this.categories = categories;
    }

    @Override
    public List<Transaction<Money, Category>> getTransactions() {
        return this.transactions;
    }

    @Override
    public List<Transaction<Money, Category>> getTransactions(TransactionType type) {
        return List.of();
    }

    @Override
    public List<Transaction<Money, Category>> getTransactions(Category category) {
        return List.of();
    }

    @Override
    public List<Transaction<Money, Category>> getTransactions(Category category, TransactionType type) {
        return List.of();
    }

    @Override
    public Set<Category> getCategories() {
        return Set.of();
    }

    @Override
    public void addTransaction(Transaction<Money, Category> transaction) {
        if(transaction == null)
            throw new NullPointerException("Transaction cannot be null");
        transactions.add(transaction);
    }

    @Override
    public void removeTransaction(Transaction<Money, Category> transaction) {
        if(transaction == null)
            throw new NullPointerException("Transaction cannot be null");
        transactions.remove(transaction);
    }
}

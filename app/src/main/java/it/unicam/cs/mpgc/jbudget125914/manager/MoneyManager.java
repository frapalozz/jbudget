package it.unicam.cs.mpgc.jbudget125914.manager;

import it.unicam.cs.mpgc.jbudget125914.categories.DefaultCategory;
import it.unicam.cs.mpgc.jbudget125914.currency.Money;
import it.unicam.cs.mpgc.jbudget125914.transaction.Transaction;

import java.util.List;
import java.util.Set;

public class MoneyManager implements TransactionsManager {

    List<Transaction<Money, DefaultCategory>> transactions;
    Set<DefaultCategory> categories;

    public MoneyManager(List<Transaction<Money, DefaultCategory>> transactions, Set<DefaultCategory> categories) {
        this.transactions = transactions;
        this.categories = categories;
    }

    @Override
    public List<Transaction<Money, DefaultCategory>> getTransactions() {
        return this.transactions;
    }

    @Override
    public List<Transaction<Money, DefaultCategory>> getTransactions(DefaultCategory category) {
        return List.copyOf(this.transactions.stream().filter(t -> t.categories().contains(category)).toList());
    }

    @Override
    public Set<DefaultCategory> getCategories() {
        return Set.copyOf(this.categories);
    }

    @Override
    public void addTransaction(Transaction<Money, DefaultCategory> transaction) {
        if(transaction == null)
            throw new NullPointerException("Transaction cannot be null");
        transactions.add(transaction);
    }

    @Override
    public void removeTransaction(Transaction<Money, DefaultCategory> transaction) {
        if(transaction == null)
            throw new NullPointerException("Transaction cannot be null");
        transactions.remove(transaction);
    }
}

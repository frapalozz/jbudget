package it.unicam.cs.mpgc.jbudget125914.manager;

import it.unicam.cs.mpgc.jbudget125914.categories.DefaultCategory;
import it.unicam.cs.mpgc.jbudget125914.currency.Currency;
import it.unicam.cs.mpgc.jbudget125914.transaction.Transaction;
import it.unicam.cs.mpgc.jbudget125914.transaction.TransactionType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public abstract class AbstractAccountManager<T extends Number, C extends Currency<T, C>> implements AccountManager<C> {

    private final List<Transaction<C>> transactions;
    private C balance;

    public AbstractAccountManager(List<Transaction<C>> transactions, C balance) {
        if(transactions == null)
            throw new NullPointerException("transactions is null");

        if(balance.isNegative())
            throw new IllegalArgumentException("balance is negative");

        this.transactions = transactions;
        this.balance = balance;
    }

    public AbstractAccountManager(C balance) {
        this(new ArrayList<>(), balance);
    }

    @Override
    public C getBalance() {
        return this.balance;
    }

    @Override
    public List<C> getBalanceMovement(DateRange dateRange) {
        return transactions
                .stream()
                .filter(t -> dateRange.containsDate(t.date()))
                .map(t -> getBalanceAtDate(t.date())).toList();
    }

    @Override
    public List<Transaction<C>> getTransactions() {
        return this.transactions;
    }

    @Override
    public List<Transaction<C>> getTransactions(List<DefaultCategory> categories, TransactionType type, DateRange dateRange) {
        return transactions.stream()
                .filter(t -> categoryFilter(t, categories) && transactionTypeFilter(t, type) && dateRangeFilter(t, dateRange))
                .toList();
    }

    @Override
    public void addTransaction(Transaction<C> transaction) {
        this.transactions.add(transaction);
        if(transaction.getType() == TransactionType.EXPENSE) {
            this.balance = this.balance.sum(transaction.amount().negate());
        }
        else {
            this.balance = transaction.amount().sum(this.balance);
        }
    }

    @Override
    public void removeTransaction(Transaction<C> transaction) {
        this.transactions.remove(transaction);
    }

    private boolean categoryFilter(Transaction<C> transaction, List<DefaultCategory> categories) {
        if(categories == null)
            return true;
        if(categories.isEmpty())
            return transaction.categories() == null || transaction.categories().isEmpty();
        return transaction.categories().containsAll(categories);
    }

    private boolean transactionTypeFilter(Transaction<C> transaction, TransactionType type) {
        if(type == null)
            return true;
        return transaction.getType() == type;
    }

    private boolean dateRangeFilter(Transaction<C> transaction, DateRange dateRange) {
        if(dateRange == null)
            return true;
        return dateRange.containsDate(transaction.date());
    }

    public Stream<Transaction<C>> transactionAfterDate(LocalDate date) {
        return transactions
                .stream()
                .filter(t -> t.date().isAfter(date));
    }
}

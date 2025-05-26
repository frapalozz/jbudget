package it.unicam.cs.mpgc.jbudget125914.manager;

import it.unicam.cs.mpgc.jbudget125914.categories.DefaultCategory;
import it.unicam.cs.mpgc.jbudget125914.currency.Currency;
import it.unicam.cs.mpgc.jbudget125914.transaction.Transaction;
import it.unicam.cs.mpgc.jbudget125914.transaction.TransactionType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class AbstractAccountManager<C extends Currency<C>> implements AccountManager<C> {

    private final Map<LocalDate, List<Transaction<C>>> transactions;
    private final Map<LocalDate, C> balance;

    public AbstractAccountManager(Map<LocalDate, List<Transaction<C>>> transactions, C balance) {
        if(transactions == null)
            throw new NullPointerException("transactions is null");

        if(balance.isNegative())
            throw new IllegalArgumentException("balance is negative");

        this.transactions = transactions;
        this.balance = new HashMap<>();
        this.balance.put(LocalDate.now(), balance);
    }

    public AbstractAccountManager(C balance) {
        this(new HashMap<>(), balance);
    }

    @Override
    public C getBalance() {
        return this.balance.get(LocalDate.now());
    }

    @Override
    public Map<LocalDate, C> getBalanceMovement(DateRange dateRange) {
        Map<LocalDate, C> balanceMovement = new HashMap<>();
        balance.keySet()
                .stream()
                .filter(dateRange::containsDate)
                .forEach(d -> balanceMovement.put(d, balance.get(d)));
        return balanceMovement;
    }

    @Override
    public List<Transaction<C>> getTransactions() {
        return this.transactions.values().stream().flatMap(List::stream).collect(Collectors.toList());
    }

    @Override
    public List<Transaction<C>> getTransactions(List<DefaultCategory> categories, TransactionType type, DateRange dateRange) {
            return transactions
                    .keySet()
                    .stream()
                    .filter(dateRange::containsDate)
                    .flatMap(d -> transactions.get(d).stream())
                    .filter(t -> transactionTypeFilter(t, type) && categoryFilter(t, categories)).toList();
    }

    @Override
    public void addTransaction(Transaction<C> transaction) {
        if(this.transactions.containsKey(transaction.date())) {
            this.transactions.get(transaction.date()).add(transaction);
        }
        else {
            List<Transaction<C>> newTransactions = new ArrayList<>();
            newTransactions.add(transaction);
            this.transactions.put(transaction.date(), newTransactions);
        }

        adjustAddBalance(transaction.date(), transaction);
    }

    @Override
    public void removeTransaction(Transaction<C> transaction) {
        this.transactions.get(transaction.date()).remove(transaction);
        adjustRemoveBalance(transaction.date(), transaction);
    }


    // TODO: Refactoring, DRY
    private void adjustAddBalance(LocalDate date, Transaction<C> transaction) {
        getBalanceMovement(new DateRange(date, LocalDate.now()))
                .keySet()
                .forEach(d -> this.balance.put(d, sumBalanceTransaction(this.balance.get(d), transaction)));
    }

    private void adjustRemoveBalance(LocalDate date, Transaction<C> transaction) {
        getBalanceMovement(new DateRange(date, LocalDate.now()))
                .keySet()
                .forEach(d -> this.balance.put(d, subBalanceTransaction(this.balance.get(d), transaction)));
    }

    private C sumBalanceTransaction(C balance, Transaction<C> transaction) {
        if(transaction.getType() == TransactionType.EXPENSE)
            return balance.subtract(transaction.amount());
        return balance.sum(transaction.amount());
    }

    private C subBalanceTransaction(C balance, Transaction<C> transaction) {
        if(transaction.getType() == TransactionType.EXPENSE)
            return balance.sum(transaction.amount());
        return balance.subtract(transaction.amount());
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
                .keySet()
                .stream()
                .filter(d -> d.isAfter(date))
                .flatMap(d -> transactions.get(d).stream());
    }
}

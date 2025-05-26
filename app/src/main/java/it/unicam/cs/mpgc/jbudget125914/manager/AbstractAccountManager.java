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
import java.util.stream.Stream;

public abstract class AbstractAccountManager<C extends Currency<C>> implements AccountManager<C> {

    private final Map<LocalDate, List<Transaction<C>>> transactions;
    private final Map<LocalDate, C> balance;

    public AbstractAccountManager(Map<LocalDate, List<Transaction<C>>> transactions, Map<LocalDate, C> balance) {
        if(transactions == null)
            throw new NullPointerException("transactions is null");

        if(balance == null)
            throw new NullPointerException("balance is null");

        this.transactions = transactions;
        this.balance = balance;
    }

    @Override
    public C getBalance() {
        return this.balance.get(balance.keySet().stream().max(LocalDate::compareTo).orElse(LocalDate.now()));
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
    public Map<LocalDate, List<Transaction<C>>> getTransactions() {
        Map<LocalDate, List<Transaction<C>>> transactions = new HashMap<>();
        this.transactions.forEach((k, v) -> transactions.put(k, List.copyOf(v)));
        return transactions;
    }

    @Override
    public Map<LocalDate, List<Transaction<C>>> getTransactions(List<DefaultCategory> categories, TransactionType type, DateRange dateRange) {
        Map<LocalDate, List<Transaction<C>>> transactions = new HashMap<>();

        buildTransactionMap(
                transactions,
                this.transactions.keySet()
                        .stream()
                    .filter(dateRange::containsDate)
                    .flatMap(d -> transactions.get(d).stream())
                    .filter(t -> transactionTypeFilter(t, type) && categoryFilter(t, categories)).toList());

        return transactions;
    }

    private void buildTransactionMap(Map<LocalDate, List<Transaction<C>>> map, List<Transaction<C>> transactions) {
        transactions.forEach(t -> addTransaction(map, t));
    }

    private void addTransaction(Map<LocalDate, List<Transaction<C>>> map, Transaction<C> transaction) {
        if(map.containsKey(transaction.date())) {
            map.get(transaction.date()).add(transaction);
        }
        else {
            List<Transaction<C>> newTransactions = new ArrayList<>();
            newTransactions.add(transaction);
            map.put(transaction.date(), newTransactions);
        }
    }

    @Override
    public void addTransaction(Transaction<C> transaction) {
        addTransaction(this.transactions, transaction);
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

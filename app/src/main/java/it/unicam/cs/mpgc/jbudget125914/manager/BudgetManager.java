package it.unicam.cs.mpgc.jbudget125914.manager;

import it.unicam.cs.mpgc.jbudget125914.transaction.Transaction;

import java.util.List;

public class BudgetManager implements TransactionsManager {

    private Range range;
    private DateRange dateRange;
    private final List<Transaction> transactions;
    private double balance;

    public BudgetManager(double balance, Range range, List<Transaction> transactions) {
        this.balance = balance;
        this.range = range;
        this.transactions = transactions;
        this.dateRange = DateRange.newRange(this.range);
    }


    @Override
    public void setRange(Range range) {
        this.range = range;

        this.dateRange = DateRange.newRange(this.range);
    }

    @Override
    public Range getRange() {
        return this.range;
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
    public double getBalance() {
        return this.balance;
    }

    @Override
    public List<Transaction> getTransactions() {
        return this.transactions.stream().filter(t -> t.date().isAfter(this.dateRange.startDate()) && t.date().isBefore(this.dateRange.endDate())).toList();
    }

    @Override
    public void addTransaction(Transaction transaction) {
        if(transaction == null)
            throw new NullPointerException("Transaction cannot be null");

        balance += transaction.value().quantity();
        transactions.add(transaction);
    }

    @Override
    public void removeTransaction(Transaction transaction) {
        if(transaction == null)
            throw new NullPointerException("Transaction cannot be null");
        transactions.remove(transaction);
    }
}

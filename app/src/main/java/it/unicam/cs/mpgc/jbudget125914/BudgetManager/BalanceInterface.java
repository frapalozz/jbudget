package it.unicam.cs.mpgc.jbudget125914.BudgetManager;

import it.unicam.cs.mpgc.jbudget125914.currency.Currency;
import it.unicam.cs.mpgc.jbudget125914.manager.DateRange;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface BalanceInterface<T extends Currency<T>> {

    T getBalance();
    Map<LocalDate, T> getBalanceMovement(DateRange dateRange);
    Map<LocalDate, T> getBalanceMovement(DateRange dateRange, String accountName);
    T getBalance(LocalDate date);
    T getBalance(String accountName);
    T getBalance(LocalDate date, String accountName);
}

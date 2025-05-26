package it.unicam.cs.mpgc.jbudget125914.BudgetManager;

import it.unicam.cs.mpgc.jbudget125914.categories.Category;
import it.unicam.cs.mpgc.jbudget125914.currency.Currency;
import it.unicam.cs.mpgc.jbudget125914.currency.Money;
import it.unicam.cs.mpgc.jbudget125914.manager.AccountManager;
import it.unicam.cs.mpgc.jbudget125914.manager.DateRange;
import it.unicam.cs.mpgc.jbudget125914.transaction.Transaction;
import it.unicam.cs.mpgc.jbudget125914.transaction.TransactionType;

import java.time.LocalDate;
import java.util.List;

public interface BudgetManager<T extends Currency<T>> {

    // Balance Interface


    // Accounts Interface
    AccountManager<T> getAccount(String accountName);
    List<AccountManager<T>> getAccounts();

    // Transactions Interface
    List<Transaction<T>> getTransactions();
    List<Transaction<T>> getTransactions(String accountName, List<Category> categories, TransactionType type, DateRange dateRange);
    void addTransaction(Transaction<T> transaction, String accountName);
    void removeTransaction(Transaction<T> transaction, String accountName);

    // Category Interface
    List<Category> getCategories();
    void addCategory(Category category);
    void removeCategory(Category category);

}

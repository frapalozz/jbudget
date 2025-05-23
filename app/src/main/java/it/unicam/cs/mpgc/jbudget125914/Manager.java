package it.unicam.cs.mpgc.jbudget125914;

import it.unicam.cs.mpgc.jbudget125914.categories.DefaultCategory;
import it.unicam.cs.mpgc.jbudget125914.manager.BudgetManager;
import it.unicam.cs.mpgc.jbudget125914.manager.DateRange;
import it.unicam.cs.mpgc.jbudget125914.transaction.Transaction;

import java.util.List;
import java.util.Set;

public interface Manager {

    double getBalance();

    double getBalance(String account);

    // Account interface
    List<BudgetManager> getAccounts();

    void addAccount(BudgetManager account, String name);

    void removeAccount(String account);


    // Range interface
    void setDateRange(DateRange dateRange);

    DateRange getDateRange();


    // Category interface
    Set<DefaultCategory> getCategories();

    void addCategory(DefaultCategory category);
    void addCategory(DefaultCategory category, int priority);

    void removeCategory(DefaultCategory category);


    // Transaction interface
    List<Transaction> getTransaction();

    List<Transaction> getTransaction(String account);

    List<Transaction> getTransaction(boolean isExpense);

    List<Transaction> getTransaction(String account, boolean isExpense);

    List<Transaction> getTransaction(DefaultCategory category);

    List<Transaction> getTransaction(List<DefaultCategory> category);

    List<Transaction> getTransaction(DefaultCategory category, boolean isExpense);

    List<Transaction> getTransaction(List<DefaultCategory> category, boolean isExpense);


    void addTransaction(Transaction transaction, String account);

    void removeTransaction(Transaction transaction, String account);


}

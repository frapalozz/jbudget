package it.unicam.cs.mpgc.jbudget125914;

import it.unicam.cs.mpgc.jbudget125914.categories.DefaultCategory;
import it.unicam.cs.mpgc.jbudget125914.manager.BudgetManager;
import it.unicam.cs.mpgc.jbudget125914.manager.DateRange;
import it.unicam.cs.mpgc.jbudget125914.transaction.Transaction;

import java.util.List;
import java.util.Set;

public interface Manager {

    // Balance Interface
    double getBalance();

    double getBalance(String accountName);

    // Account interface
    List<BudgetManager> getAccounts();

    BudgetManager getAccount(String accountName);

    void addAccount(BudgetManager account, String accountName);

    void removeAccount(String accountName);


    // Category interface

    /**
     * Return all the Categories created
     * @return all the Categories created
     */
    Set<DefaultCategory> getCategories();

    /**
     * Add a new Category
     * @param category the category to add
     */
    void addCategory(DefaultCategory category);

    /**
     * Add a new Category with a priority
     * @param category the category to add
     * @param priority the priority for the specified category
     */
    void addCategory(DefaultCategory category, int priority);

    /**
     * Edit the priority of an existing Category
     * @param category the category specified to modify the priority
     * @param priority the new priority
     */
    void editCategoryPriority(DefaultCategory category, int priority);

    /**
     * Remove a specified category
     * @param category the specified category to remove
     */
    void removeCategory(DefaultCategory category);


    // Transaction interface

    /**
     * Return all the Transactions
     * @return all the Transactions
     */
    List<Transaction> getTransactions();

    /**
     * Return the list of the Transaction after the filtering
     * @param account account selected, if {@code null} select all Transaction from all accounts
     * @param isExpense filter Transaction based on type, if {@code null} no type filter
     * @param categories filter Transaction based on categories, if {@code null} no categories filter
     * @return the list of the Transaction after the filtering
     */
    List<Transaction> getTransaction(String account, Boolean isExpense, List<DefaultCategory> categories, DateRange dateRange);

    /**
     * Add new Transaction to a specified account
     * @param transaction the Transaction to add
     * @param account the specified account
     */
    void addTransaction(Transaction transaction, String account);

    /**
     * Remove a Transaction from a specified account
     * @param transaction the Transaction to remove
     * @param account the specified account
     */
    void removeTransaction(Transaction transaction, String account);


}

/**
 * MIT License
 * Copyright (c) 2025 Francesco Palozzi
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */


package it.unicam.cs.mpgc.jbudget125914.manager;

import it.unicam.cs.mpgc.jbudget125914.categories.DefaultCategory;
import it.unicam.cs.mpgc.jbudget125914.currency.Currency;
import it.unicam.cs.mpgc.jbudget125914.transaction.Transaction;
import it.unicam.cs.mpgc.jbudget125914.transaction.TransactionType;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 *
 */
public interface AccountManager<T extends Currency<T>> {

    /**
     * Return the current balance
     * @return the current balance
     */
    T getBalance();

    /**
     * Return the balance at the specified date
     * @param date the date where to get the balance
     * @return the balance at the specified date
     */
    T getBalanceAtDate(LocalDate date);

    /**
     * Return the balance history movement
     * @param dateRange the date range to filter the balance movement
     * @return the balance history movement
     */
    Map<LocalDate, T> getBalanceMovement(DateRange dateRange);

    /**
     * Return the transaction of the current DateRange
     * @return the transaction of the current DateRange
     */
    List<Transaction<T>> getTransactions();

    /**
     * Return the transaction filtered by category, type and dateRange
     * @param category the category to select, if {@code null} no category filter
     * @param type the type to select, if {@code null} no type filter
     * @param dateRange the date range to filter, if {@code null} no date range filter
     * @return the transaction filtered by category, type and dateRange
     */
    List<Transaction<T>> getTransactions(List<DefaultCategory> category, TransactionType type, DateRange dateRange);

    /**
     * Add a new Transaction
     * @param transaction the new Transaction
     * @throws NullPointerException if transaction is {@code null}
     */
    void addTransaction(Transaction<T> transaction);

    /**
     * Remove a Transaction
     * @param transaction the Transaction to remove
     * @throws NullPointerException if transaction is {@code null}
     */
    void removeTransaction(Transaction<T> transaction);
}

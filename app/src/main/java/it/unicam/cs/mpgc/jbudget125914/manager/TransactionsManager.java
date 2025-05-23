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
import it.unicam.cs.mpgc.jbudget125914.currency.Money;
import it.unicam.cs.mpgc.jbudget125914.transaction.Transaction;

import java.util.List;
import java.util.Set;

/**
 *
 */
public interface TransactionsManager {

    /**
     * Set the new range
     * @param range the new range
     */
    void setRange(Range range);

    /**
     * Return the range
     * @return the range
     */
    Range getRange();

    /**
     * Set the new DateRange
     * @param dateRange the new DateRange
     */
    void setDateRange(DateRange dateRange);

    /**
     * Return the DateRange
     * @return the DateRange
     */
    DateRange getDateRange();

    /**
     * Return the transaction of the current DateRange
     * @return the transaction of the current DateRange
     */
    List<Transaction> getTransactions();

    /**
     * Add a new Transaction
     * @param transaction the new Transaction
     * @throws NullPointerException if transaction is {@code null}
     */
    void addTransaction(Transaction transaction);

    /**
     * Remove a Transaction
     * @param transaction the Transaction to remove
     * @throws NullPointerException if transaction is {@code null}
     */
    void removeTransaction(Transaction transaction);
}

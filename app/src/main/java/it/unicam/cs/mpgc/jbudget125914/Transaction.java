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

package it.unicam.cs.mpgc.jbudget125914;

import java.util.Date;
import java.util.Set;

/**
 * The Transaction interface gives the user a simple way to access to the name, value, date and Categories
 * of a Transaction
 * @author Francesco Palozzi
 */
public interface Transaction<T, C> {

    /**
     * Returns the name of this Transaction
     * @return the name of this Transaction
     */
    String getName();

    /**
     * Create a new {@code Transaction} with the new name passed
     * @param name the new name for the Transaction
     * @throws NullPointerException if name is {@code null}
     * @return the new {@code Transaction}
     */
    Transaction<T, C> setName(String name);

    /**
     * Returns the value of this Transaction
     * @return the value of this Transaction
     */
    T getValue();

    /**
     * Create a new {@code Transaction} with the new value passed
     * @param value the new value for the Transaction
     * @throws NullPointerException if value is {@code null}
     * @return the new {@code Transaction}
     */
    Transaction<T, C> setValue(T value);

    /**
     * Returns the date when this Transaction occurred
     * @return the date when this Transaction occurred
     */
    Date getDate();

    /**
     * Create a new {@code Transaction} with the new date passed
     * @param date the new date for the Transaction
     * @throws NullPointerException if date is {@code null}
     * @return the new {@code Transaction}
     */
    Transaction<T, C> setDate(Date date);

    /**
     * Returns the Category set of this Transaction
     * @return the Category set of this Transaction
     */
    Set<C> getCategory();

    /**
     * Create a new {@code Transaction} with the new category set passed
     * @param categories the new category set for the Transaction
     * @throws NullPointerException if categories is {@code null}
     * @return the new {@code Transaction}
     */
    Transaction<T, C> setCategory(Set<C> categories);

    /**
     * Return the type of this Transaction
     * @return the type of this Transaction
     */
    TransactionType getTransactionType();

    /**
     * Create a new {@code Transaction} with the new {@code TransactionType} passed
     * @param type the new {@code TransactionType} for the Transaction
     * @throws NullPointerException if type is {@code null}
     * @return the new {@code Transaction}
     */
    Transaction<T, C> setTransactionType(TransactionType type);
}

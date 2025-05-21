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
import java.util.List;

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
     * Set the new name for this Transaction
     * @param name the new name of the Transaction
     * @throws NullPointerException if name is {@code null}
     */
    void setName(String name);

    /**
     * Returns the value of this Transaction
     * @return the value of this Transaction
     */
    T getValue();

    /**
     * Set the new value for this Transaction
     * @param value the new value of the Transaction
     * @throws NullPointerException if value is {@code null}
     */
    void setValue(T value);

    /**
     * Returns the date when this Transaction occurred
     * @return the date when this Transaction occurred
     */
    Date getDate();

    /**
     * Set the new date for this Transaction
     * @param date the new date of the Transaction
     * @throws NullPointerException if date is {@code null}
     */
    void setDate(Date date);

    /**
     * Returns the list of Category of this Transaction
     * @return the list of Category of this Transaction
     */
    List<C> getCategory();

    /**
     * Set the new category list for this Transaction
     * @param categories the new category list for the Transaction
     * @throws NullPointerException if categories is {@code null}
     */
    void setCategory(List<C> categories);

    /**
     * Return the type of this Transaction
     * @return the type of this Transaction
     */
    TransactionType getTransactionType();
}

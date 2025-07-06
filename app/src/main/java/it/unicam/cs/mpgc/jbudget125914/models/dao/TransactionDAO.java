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

package it.unicam.cs.mpgc.jbudget125914.models.dao;

import it.unicam.cs.mpgc.jbudget125914.models.entities.transaction.Transaction;
import lombok.NonNull;

import java.time.temporal.Temporal;
import java.util.List;
import java.util.Set;

/**
 * Transaction DAO interface contract
 * @param <T> transaction type
 * @param <A> account type
 * @param <TA> tag type
 * @param <N> number type
 * @param <D> data type
 * @param <AM> amount type
 * @param <G> group type
 */
public interface TransactionDAO<T extends Transaction<AM,D,TA,A>, A, TA, N extends Number, D extends Temporal & Comparable<? super D>, AM, G> {

    /**
     * Return all the transaction between date {@code from} and {@code to} (inclusive) and filtered by accounts and tags
     * @param from date start
     * @param to date end
     * @param accounts accounts filter
     * @param tags tags filter
     * @param group group filter
     * @return all the transaction between date {@code from} and {@code to} (inclusive) and filtered by accounts and tags
     */
    List<T> findAll(@NonNull D from, @NonNull D to, @NonNull Set<A> accounts, @NonNull Set<TA> tags, @NonNull G group);

    /**
     * Return the amount of all period to the cutoff (inclusive)
     * @param cutoff cutoff date
     * @param amountClass amount class
     * @param numberClass number class (of amount attribute)
     * @param accounts accounts filter
     * @return the total amount period to the cutoff (inclusive)
     */
    AM getTransactionAmount(@NonNull D cutoff, @NonNull Class<AM> amountClass, @NonNull Class<N> numberClass, @NonNull Set<A> accounts);
}

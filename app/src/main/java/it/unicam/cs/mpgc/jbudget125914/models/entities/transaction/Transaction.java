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

package it.unicam.cs.mpgc.jbudget125914.models.entities.transaction;

import it.unicam.cs.mpgc.jbudget125914.interfaces.Describable;
import it.unicam.cs.mpgc.jbudget125914.interfaces.AccountLinked;
import it.unicam.cs.mpgc.jbudget125914.interfaces.Taggable;
import lombok.NonNull;

/**
 * This interface represent a Transaction
 * @param <AM> amount type
 * @param <D> date type
 * @param <T> tag type
 * @param <A> account type
 */
public interface Transaction<
        AM, D, T, A
        > extends Taggable<T>, AccountLinked<A>, Describable<String> {

    /**
     * Return the transaction ID
     * @return the transaction ID
     */
    Long getTransactionId();

    /**
     * Return the Transaction amount
     * @return the Transaction amount
     */
    AM getAmount();

    /**
     * Set the new Transaction amount
     * @param amount the new Transaction amount
     * @throws NullPointerException if {@code amount} is null
     * @throws IllegalArgumentException if {@code amount} is ZERO
     */
    void setAmount(@NonNull AM amount);

    /**
     * Return the Transaction date
     * @return the Transaction date
     */
    D getDate();

    /**
     * Set the new Transaction date
     * @param date the new Transaction date
     * @throws NullPointerException if {@code date} is null
     */
    void setDate(@NonNull D date);
}

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

import it.unicam.cs.mpgc.jbudget125914.models.entities.account.Account;
import it.unicam.cs.mpgc.jbudget125914.models.entities.account.AccountLinked;
import it.unicam.cs.mpgc.jbudget125914.models.entities.category.Category;
import it.unicam.cs.mpgc.jbudget125914.models.entities.tag.Tag;
import it.unicam.cs.mpgc.jbudget125914.models.entities.tag.Taggable;
import lombok.NonNull;

import java.time.temporal.Temporal;

/**
 * This interface represent a Transaction
 * @param <D> date type
 * @param <T> tag type
 * @param <A> account type
 */
public interface Transaction<
        N extends Number,
        D extends Temporal,
        T extends Tag<? extends Category<T>>,
        A extends Account<N>
        > extends Taggable<T>, AccountLinked<A> {

    /**
     * Return the Transaction ID
     * @return the Transaction ID
     */
    Long getTransactionId();

    /**
     * Return the Transaction amount
     * @return the Transaction amount
     */
    N getAmount();

    /**
     * Set the new Transaction amount
     * @param amount the new Transaction amount
     * @throws NullPointerException if {@code amount} is null
     * @throws IllegalArgumentException if {@code amount} is ZERO
     */
    void setAmount(@NonNull N amount);

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

    /**
     * Return the Transaction description
     * @return the Transaction description
     */
    String getDescription();

    /**
     * Set the new Transaction description
     * @param description the new Transaction description
     * @throws NullPointerException if {@code description} is null
     */
    void setDescription(@NonNull String description);
}

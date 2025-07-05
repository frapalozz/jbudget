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

package it.unicam.cs.mpgc.jbudget125914.controller.manager.filterManager;

import it.unicam.cs.mpgc.jbudget125914.models.embeddable.amount.Amount;
import it.unicam.cs.mpgc.jbudget125914.models.entities.account.Account;
import it.unicam.cs.mpgc.jbudget125914.models.entities.category.Category;
import it.unicam.cs.mpgc.jbudget125914.models.entities.group.Group;
import it.unicam.cs.mpgc.jbudget125914.models.entities.tag.Tag;
import it.unicam.cs.mpgc.jbudget125914.models.entities.transaction.Transaction;

import java.time.temporal.Temporal;
import java.util.Set;

/**
 * FilterManager gives you the ability to set the filter for the next fetch or to save the selected items
 * @param <T> transaction type
 * @param <S> schedule type
 * @param <AM> amount type
 * @param <N> number type
 * @param <A> account type
 * @param <D> data type
 * @param <TA> tag type
 * @param <C> category type
 * @param <G> group type
 */
public interface FilterManager<
        T extends Transaction<AM,D,TA,A>,
        S extends Transaction<AM,D,TA,A>,
        AM extends Amount<N, AM>,
        N extends Number,
        A extends Account<AM>,
        D extends Temporal & Comparable<? super D>,
        TA extends Tag<C>,
        C extends Category<TA>,
        G extends Group<TA,C,?,A>> {

    /**
     * Return the current Group
     * @return the current Group
     */
    G getGroup();

    /**
     * Set the new Group
     * @param group the new Group
     */
    void setGroup(G group);

    /**
     * Return the start date
     * @return the start date
     */
    D getStartDate();

    /**
     * Set the new start date
     * @param startDate the new start date
     */
    void setStartDate(D startDate);

    /**
     * Return the end date
     * @return the end date
     */
    D getEndDate();

    /**
     * Set the new end date
     * @param endDate the new end date
     */
    void setEndDate(D endDate);

    /**
     * Return the set of accounts
     * @return the set of accounts
     */
    Set<A> getAccounts();

    /**
     * Set the new set of accounts
     * @param accounts the new set of accounts
     */
    void setAccounts(Set<A> accounts);

    /**
     * Return the set of tags
     * @return the set of tags
     */
    Set<TA> getTags();

    /**
     * Set the new set of tags
     * @param tags the new set of tags
     */
    void setTags(Set<TA> tags);

    /**
     * Return the transaction
     * @return the transaction
     */
    T getTransaction();

    /**
     * Set the new transaction
     * @param transaction the new transaction
     */
    void setTransaction(T transaction);

    /**
     * Return the schedule
     * @return the schedule
     */
    S getSchedule();

    /**
     * Set the new schedule
     * @param schedule the new schedule
     */
    void setSchedule(S schedule);
}

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

package it.unicam.cs.mpgc.jbudget125914.controller.manager.daoManager;

import it.unicam.cs.mpgc.jbudget125914.models.dao.GeneralDAO;
import it.unicam.cs.mpgc.jbudget125914.models.dao.ScheduleDAO;
import it.unicam.cs.mpgc.jbudget125914.models.dao.TransactionDAO;
import it.unicam.cs.mpgc.jbudget125914.models.embeddable.amount.Amount;
import it.unicam.cs.mpgc.jbudget125914.models.entities.account.Account;
import it.unicam.cs.mpgc.jbudget125914.models.entities.category.Category;
import it.unicam.cs.mpgc.jbudget125914.models.entities.group.Group;
import it.unicam.cs.mpgc.jbudget125914.models.entities.tag.Tag;
import it.unicam.cs.mpgc.jbudget125914.models.entities.transaction.Transaction;

import java.time.temporal.Temporal;

/**
 * GeneralManager is an interface that gives the ability to access the DAO
 * @param <T> transaction type
 * @param <S> schedule type
 * @param <AM> amount type
 * @param <N> number type
 * @param <A> account type
 * @param <D> data type
 * @param <TA> tag type
 * @param <C> category type
 * @param <G> group type
 * @param <TD> transaction DAO type
 * @param <AD> account DAO type
 * @param <CD> category DAO type
 * @param <GD> group DAO type
 * @param <TAD> tag DAO type
 * @param <SD> schedule DAO type
 */
public interface DaoManager<
        T extends Transaction<AM,D,TA,A>,
        S extends Transaction<AM,D,TA,A>,
        AM extends Amount<N, AM>,
        N extends Number,
        A extends Account<AM>,
        D extends Temporal & Comparable<? super D>,
        TA extends Tag<C>,
        C extends Category<TA>,
        G extends Group<TA,C,?,A>,
        TD extends TransactionDAO<T, A, TA, N, D, AM, G>,
        AD extends GeneralDAO<A>,
        CD extends GeneralDAO<C>,
        GD extends GeneralDAO<G>,
        TAD extends GeneralDAO<TA>,
        SD extends ScheduleDAO<S, A, TA, N, D, AM, G>> {

    /**
     * Return the transaction DAO
     * @return the transaction DAO
     */
    TD getTransactionDAO();

    /**
     * Return the account DAO
     * @return the account DAO
     */
    AD getAccountDAO();

    /**
     * Return the category DAO
     * @return the category DAO
     */
    CD getCategoryDAO();

    /**
     * Return the group DAO
     * @return the group DAO
     */
    GD getGroupDAO();

    /**
     * Return the tag DAO
     * @return the tag DAO
     */
    TAD getTagDAO();

    /**
     * Return the schedule DAO
     * @return the schedule DAO
     */
    SD getScheduleDAO();
}

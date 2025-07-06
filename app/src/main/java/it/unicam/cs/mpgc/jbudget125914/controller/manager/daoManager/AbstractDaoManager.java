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
import lombok.Getter;

import java.time.temporal.Temporal;

/**
 * AbstractGeneralManager is an abstract class for all the general manager
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
@Getter
public abstract class AbstractDaoManager<
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
        SD extends ScheduleDAO<S, A, TA, N, D, AM, G>> implements DaoManager<T,S,AM,N,A,D,TA,C,G,TD,AD,CD,GD,TAD,SD> {

    private final TD transactionDAO;

    private final AD accountDAO;

    private final CD categoryDAO;

    private final GD groupDAO;

    private final TAD tagDAO;

    private final SD scheduleDAO;

    public AbstractDaoManager(TD td, AD ad, CD cd, GD gd, TAD tad, SD sd) {
        this.transactionDAO = td;
        this.accountDAO = ad;
        this.categoryDAO = cd;
        this.groupDAO = gd;
        this.tagDAO = tad;
        this.scheduleDAO = sd;

    }
}

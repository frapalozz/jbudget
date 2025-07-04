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

package it.unicam.cs.mpgc.jbudget125914.models.services;

import it.unicam.cs.mpgc.jbudget125914.models.services.util.CriteriaQueryHelper;
import it.unicam.cs.mpgc.jbudget125914.models.services.util.TransactionUtil;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;
import lombok.NonNull;

import java.time.temporal.Temporal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This class represent a TransactionService, it is used to get specified Transaction entity from the database
 * @param <T> transaction type
 * @param <A> account type
 * @param <TA> nag type
 * @param <N> number type (of the amount)
 * @param <D> date type
 * @param <AM> amount type
 */
public class TransactionService<
        T, A, TA, N extends Number, D extends Temporal & Comparable<? super D>, AM, G
        > extends AbstractService<T> {

    /**
     * TransactionService constructor
     * @param transactionClass transaction class
     * @throws NullPointerException if {@code transactionClass} is null
     */
    public TransactionService(Class<T> transactionClass) {
        super(transactionClass);
    }

    /**
     * Return all the transaction between date {@code from} and {@code to} (inclusive) and filtered by account
     * @param from date start
     * @param to date end
     * @param accounts account filter
     * @return all the transaction between date {@code from} and {@code to} (inclusive) and filtered by accounts
     */
    public List<T> findAll(D from, D to, @NonNull Set<A> accounts, G group) {
        return findAll(from, to, accounts, new HashSet<>(), group);
    }

    /**
     * Return all the transaction between date {@code from} and {@code to} (inclusive) and filtered by accounts and tags
     * @param from date start
     * @param to date end
     * @param accounts accounts filter
     * @param tags tags filter
     * @return all the transaction between date {@code from} and {@code to} (inclusive) and filtered by accounts and tags
     */
    public List<T> findAll(D from, D to, @NonNull Set<A> accounts, @NonNull Set<TA> tags, G group) {

        return TransactionUtil.executeInTransactionReturn(em -> {
            CriteriaQueryHelper<T, T> helper = new CriteriaQueryHelper<>(em, this.getEntityClass(), this.getEntityClass());

            Predicate groupPredicate = helper.getRoot().get("account").get("groupId").equalTo(group);
            Predicate datePredicate = helper.getCb().between(helper.getRoot().get("date"), from, to);
            Predicate accountPredicate = helper.in("account", accounts);
            Predicate tagsPredicate = helper.containsAny("tags", tags);

            helper.where(helper.getCb().and(datePredicate, accountPredicate, tagsPredicate, groupPredicate));

            return helper.getResultList();
        });
    }

    /**
     * Return the amount of all period to the cutoff (inclusive)
     * @param cutoff cutoff date
     * @param amountClass amount class
     * @param numberClass number class (of amount attribute)
     * @return the total amount period to the cutoff (inclusive)
     */
    public AM getTransactionAmount(D cutoff, Class<AM> amountClass, Class<N> numberClass, Set<A> accounts) {
        return TransactionUtil.executeInTransactionReturn(em -> {
            CriteriaQueryHelper<T, N> helper = new CriteriaQueryHelper<>(em, getEntityClass(), numberClass);

            Predicate accountPredicate = helper.in("account", accounts);
            Predicate datePredicate = helper.getCb().lessThanOrEqualTo(helper.getRoot().get("date"), cutoff);

            helper.where(accountPredicate, datePredicate);

            Expression<N> transactionSum = helper.getCb().sum(
                    helper.getRoot().get("amount").get("amount")
            );

            helper.getCq().select(transactionSum);

            return helper.convertor(amountClass, numberClass);
        });
    }
}

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

import it.unicam.cs.mpgc.jbudget125914.models.dao.util.CriteriaQueryHelper;
import it.unicam.cs.mpgc.jbudget125914.models.dao.util.TransactionUtil;
import it.unicam.cs.mpgc.jbudget125914.models.entities.transaction.Recurrence;
import it.unicam.cs.mpgc.jbudget125914.models.entities.transaction.Transaction;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;
import lombok.NonNull;

import java.time.Period;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAmount;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * This class represent a TransactionService, it is used to get specified Transaction entity from the database
 * @param <T> transaction type
 * @param <A> account type
 * @param <TA> nag type
 * @param <N> number type (of the amount)
 * @param <D> date type
 * @param <AM> amount type
 * @param <G> group type
 */
public class TransactionDAO<
        T extends Transaction<AM,D,TA,A>, A, TA, N extends Number, D extends Temporal & Comparable<? super D>, AM, G
        > extends AbstractDAO<T> {

    /**
     * TransactionService constructor
     * @param transactionClass transaction class
     * @throws NullPointerException if {@code transactionClass} is null
     */
    public TransactionDAO(Class<T> transactionClass) {
        super(transactionClass);
    }

    /**
     * Generate the recurrence transactions
     * @param startDate start date of the recurrence
     * @param endDate end date of the recurrence
     * @param recurrence recurrence frequency
     * @param transaction transaction data
     * @param cloner transaction cloner
     */
    public void createRecurrenceTransactions(@NonNull D startDate, @NonNull D endDate, Recurrence recurrence, T transaction, Function<T, T> cloner) {
        List<T> transactions = new ArrayList<>();

        TemporalAmount plusDays = switch (recurrence) {
            case DAILY -> Period.ofDays(1);
            case WEEKLY -> Period.ofDays(7);
            case MONTHLY -> Period.ofDays(30);
            case YEARLY -> Period.ofDays(365);
        };

        Stream.iterate(startDate, date -> date.compareTo(endDate) <= 0, date -> (D) date.plus(plusDays))
                .forEach(t -> {
                    T clone = cloner.apply(transaction);
                    clone.setDate(t);
                    transactions.add(clone);
                });

        create(transactions);
    }

    /**
     * Return all the transaction between date {@code from} and {@code to} (inclusive) and filtered by accounts and tags
     * @param from date start
     * @param to date end
     * @param accounts accounts filter
     * @param tags tags filter
     * @param group group filter
     * @return all the transaction between date {@code from} and {@code to} (inclusive) and filtered by accounts and tags
     */
    public List<T> findAll(@NonNull D from, @NonNull D to, @NonNull Set<A> accounts, @NonNull Set<TA> tags, @NonNull G group) {

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
     * @param accounts accounts filter
     * @return the total amount period to the cutoff (inclusive)
     */
    public AM getTransactionAmount(@NonNull D cutoff, @NonNull Class<AM> amountClass, @NonNull Class<N> numberClass, @NonNull Set<A> accounts) {
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

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

package it.unicam.cs.mpgc.jbudget125914.models.repository.transaction;

import it.unicam.cs.mpgc.jbudget125914.service.filterService.FinancialFilterService;
import it.unicam.cs.mpgc.jbudget125914.models.repository.util.CriteriaQueryHelper;
import it.unicam.cs.mpgc.jbudget125914.models.repository.util.TransactionUtil;
import it.unicam.cs.mpgc.jbudget125914.models.embeddable.amount.FinancialAmount;
import it.unicam.cs.mpgc.jbudget125914.models.entities.transaction.FinancialTransaction;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;
import lombok.NonNull;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * This class represent a TransactionService, it is used to get specified Transaction entity from the database
 */
public class FinancialTransactionRepository extends AbstractFinancialTransactionRepository<FinancialTransaction> implements
        TransactionRepository<FinancialTransaction, FinancialFilterService>,
        Balance<FinancialAmount, FinancialFilterService> {

    /**
     * TransactionService constructor
     */
    public FinancialTransactionRepository() {
        super(FinancialTransaction.class);
    }

    @Override
    public FinancialAmount getBalance(@NonNull FinancialFilterService filter) {
        return TransactionUtil.executeInTransactionReturn(em -> {
            CriteriaQueryHelper<FinancialTransaction, BigDecimal> helper = new CriteriaQueryHelper<>(em, getEntityClass(), BigDecimal.class);

            Predicate accountPredicate = helper.in("account", filter.getAccounts());
            Predicate datePredicate = helper.getCb().lessThanOrEqualTo(helper.getRoot().get("date"), LocalDate.now());

            helper.where(accountPredicate, datePredicate);

            Expression<BigDecimal> transactionSum = helper.getCb().sum(
                    helper.getRoot().get("amount").get("amount")
            );

            helper.getCq().select(transactionSum);

            return new FinancialAmount(helper.getResultList().getFirst());
        });
    }
}

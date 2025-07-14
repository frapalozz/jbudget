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

package it.unicam.cs.mpgc.jbudget125914.service.repositoryService;

import it.unicam.cs.mpgc.jbudget125914.models.repository.transaction.FinancialScheduleRepository;
import it.unicam.cs.mpgc.jbudget125914.models.repository.GeneralRepository;
import it.unicam.cs.mpgc.jbudget125914.models.repository.transaction.FinancialTransactionRepository;
import it.unicam.cs.mpgc.jbudget125914.models.entities.account.FinancialAccount;
import it.unicam.cs.mpgc.jbudget125914.models.entities.category.FinancialCategory;
import it.unicam.cs.mpgc.jbudget125914.models.entities.group.FinancialGroup;
import it.unicam.cs.mpgc.jbudget125914.models.entities.tag.FinancialTag;
import it.unicam.cs.mpgc.jbudget125914.models.entities.transaction.FinancialTransaction;
import lombok.NonNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * This class represent a Financial Repository Manager
 */
public final class FinancialRepositoryService extends AbstractRepositoryService<
        FinancialTransaction,
        LocalDate,
        FinancialTransactionRepository,
        GeneralRepository<FinancialAccount>,
        GeneralRepository<FinancialCategory>,
        GeneralRepository<FinancialGroup>,
        GeneralRepository<FinancialTag>,
        FinancialScheduleRepository> {

    public FinancialRepositoryService() {
        super(
                new FinancialTransactionRepository(),
                new GeneralRepository<>(FinancialAccount.class),
                new GeneralRepository<>(FinancialCategory.class),
                new GeneralRepository<>(FinancialGroup.class),
                new GeneralRepository<>(FinancialTag.class),
                new FinancialScheduleRepository()
        );
    }

    @Override
    public void generateRecurrentTransactions(@NonNull LocalDate startDate, @NonNull LocalDate endDate, Recurrence recurrence, FinancialTransaction transaction) {

        getTransactionRepository().create(transactionGenerator(
                new ArrayList<>(),
                startDate,
                endDate,
                transaction,
                Recurrence.recurrenceAmount(recurrence)));
    }

    private List<FinancialTransaction> transactionGenerator(List<FinancialTransaction> transactions, LocalDate startDate, LocalDate endDate, FinancialTransaction transaction, int days) {
        Stream.iterate(startDate, date -> date.minusDays(1).isBefore(endDate), date -> date.plusDays(days))
                .forEach(d -> {
                    FinancialTransaction clone = new FinancialTransaction(
                            transaction.getAmount(),
                            d,
                            transaction.getDescription(),
                            transaction.getAccount(),
                            transaction.getTags());
                    transactions.add(clone);
                });

        return transactions;
    }
}

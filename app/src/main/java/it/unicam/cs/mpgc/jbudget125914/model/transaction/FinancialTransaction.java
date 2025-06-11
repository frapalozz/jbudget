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

package it.unicam.cs.mpgc.jbudget125914.model.transaction;

import it.unicam.cs.mpgc.jbudget125914.model.account.FinancialAccount;
import it.unicam.cs.mpgc.jbudget125914.model.tag.FinancialTag;
import it.unicam.cs.mpgc.jbudget125914.model.transaction.details.FinancialDetail;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Set;


/**
 * This abstract class provides a default implementation of a transaction that abstracts a general transaction.
 */
@Entity
@ToString
@Getter
@Setter
@NoArgsConstructor
@Table(name = "financial_transactions")
public class FinancialTransaction extends AbstractTransaction<FinancialDetail, FinancialTransaction, FinancialTag> {

    @ManyToOne
    @JoinColumn(name = "accountId")
    private FinancialAccount account;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "financial_transaction_tags",
            joinColumns = @JoinColumn(name = "transactionId", table = "financial_transactions"),
            inverseJoinColumns = @JoinColumn(name = "tag_name", table = "financial_tags")
    )
    private Set<FinancialTag> tags;

    public FinancialTransaction(Long transactionId, FinancialDetail details, LocalDate date, String description, FinancialAccount account, Set<FinancialTag> tags) {
        super(transactionId, details, date, description);

        this.account = account;
        this.tags = tags;
    }

    @Override
    public Set<FinancialTag> getTags() {
        return Set.copyOf(tags);
    }
}

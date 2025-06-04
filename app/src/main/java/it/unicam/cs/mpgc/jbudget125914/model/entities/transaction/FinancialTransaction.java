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

package it.unicam.cs.mpgc.jbudget125914.model.entities.transaction;

import it.unicam.cs.mpgc.jbudget125914.model.entities.account.Account;
import it.unicam.cs.mpgc.jbudget125914.model.entities.account.FinancialAccount;
import it.unicam.cs.mpgc.jbudget125914.model.entities.category.FinancialTag;
import it.unicam.cs.mpgc.jbudget125914.model.entities.category.Tag;
import it.unicam.cs.mpgc.jbudget125914.model.entities.transaction.details.FinancialDetail;
import jakarta.persistence.*;
import org.hibernate.annotations.Immutable;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


/**
 * This abstract class provides a default implementation of a transaction that abstracts a general transaction.
 */
@Entity
@Immutable
@Table(name = "financial_transactions")
public final class FinancialTransaction extends AbstractTransaction<FinancialDetail, FinancialTransaction> {

    @ManyToOne
    @JoinColumn(name = "account_id")
    private final FinancialAccount account;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "financial_transaction_tags",
            joinColumns = @JoinColumn(name = "transaction_id", table = "financial_transactions"),
            inverseJoinColumns = @JoinColumn(name = "tag_name", table = "financial_tags")
    )
    private final Set<FinancialTag> tags;

    public FinancialTransaction(Long transactionId, FinancialDetail details, LocalDate date, String description, FinancialAccount account, Set<FinancialTag> tags) {
        super(transactionId, details, date, description);

        this.account = account;
        this.tags = tags;
    }

    public FinancialTransaction() {
        super();
        this.tags = new HashSet<>();
        this.account = new FinancialAccount();
    }

    @Override
    public Set<Tag<FinancialTransaction>> getTags() {
        if(tags == null)
            return null;
        return Set.copyOf(tags);
    }

    @Override
    public Account<FinancialDetail, FinancialTransaction> getAccount() {
        return account;
    }

    @Override
    public String toString() {
        return "Financial Transaction " + super.toString() + account +  " | Tags: " + tags + "]";
    }
}

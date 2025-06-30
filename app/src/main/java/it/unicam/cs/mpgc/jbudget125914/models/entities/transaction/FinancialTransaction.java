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

import it.unicam.cs.mpgc.jbudget125914.models.entities.account.FinancialAccount;
import it.unicam.cs.mpgc.jbudget125914.models.entities.tag.FinancialTag;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;


/**
 * This class represent a FinancialTransaction entity
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "transaction")
public class FinancialTransaction implements Transaction<BigDecimal, LocalDate, FinancialTag, FinancialAccount>, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    @Column(columnDefinition = "DATE")
    private LocalDate date;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "account")
    private FinancialAccount account;

    @Column(precision = 14, scale = 2, nullable = false)
    private BigDecimal amount;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "transaction_tag_relationship",
            joinColumns = @JoinColumn(name = "transactionid", table = "transaction"),
            inverseJoinColumns = @JoinColumn(name = "tagid", table = "tag")
    )
    private Set<FinancialTag> tags;

    /**
     * Construct a new FinancialTransaction
     * @param amount amount of the Transaction
     * @param date date of the Transaction
     * @param description description of the Transaction
     * @param account account associated to the Transaction
     * @param tags tags associated to the Transaction
     * @throws NullPointerException if any of the params are null
     * @throws IllegalArgumentException if {@code amount} is ZERO
     */
    public FinancialTransaction(
            @NonNull BigDecimal amount,
            @NonNull LocalDate date,
            @NonNull String description,
            @NonNull FinancialAccount account,
            @NonNull Set<FinancialTag> tags) {

        setAccount(account);
        setTags(tags);
        setAmount(amount);
        setDate(date);
        setDescription(description);
    }

    @Override
    public void setAmount(@NonNull BigDecimal amount) {
        if(amount.equals(BigDecimal.ZERO))
            throw new IllegalArgumentException("Amount must be greater or less than zero");
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Financial Transaction [account: " + account +
                ", amount: " + amount +
                ", date: " + this.getDate() +
                ", description: " + this.getDescription() +
                ", tags: " + tags + "]";
    }
}

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

import it.unicam.cs.mpgc.jbudget125914.model.account.Account;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;


/**
 * This abstract class provides a default implementation of a transaction that abstracts a general transaction.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Transaction implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transaction_id;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(precision = 10, scale = 2)
    private BigDecimal amount;

    @Column(columnDefinition = "DATE")
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    public Transaction(String description, BigDecimal amount, LocalDate date, TransactionType type) {
        if(date == null)
            throw new NullPointerException("date for the transaction cannot be null");
        if(amount == null || amount.equals(BigDecimal.ZERO))
            throw new IllegalArgumentException("value for the transaction cannot be zero");

        this.description = description;
        this.amount = amount;
        this.date = date;
        this.type = type;
    }

    @Override
    public String toString() {

        return "description: " + this.description + "\n" +
                "type: " + this.type + "\n" +
                "value: " + this.amount + "\n" +
                "date: " + this.date.toString() + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if(o == null)
            return false;
        if(!(o instanceof Transaction that))
            return false;
        if(this == o)
            return true;

        if(!this.description.equals(that.getDescription()))
            return false;
        if(!this.amount.equals(that.getAmount()))
            return false;
        if(!this.date.equals(that.getDate()))
            return false;
        return this.getType() == that.getType();
    }
}

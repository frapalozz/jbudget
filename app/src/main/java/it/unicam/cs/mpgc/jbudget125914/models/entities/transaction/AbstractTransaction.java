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

import it.unicam.cs.mpgc.jbudget125914.models.embeddable.amount.Amount;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;


/**
 * AbstractTransaction is an abstract class for all the transaction entities
 * It has an ID, date, description and amount
 * @param <AM> amount type
 * @param <D> data type
 * @param <TA> tag type
 * @param <A> account type
 */
@MappedSuperclass
@Getter
@Setter
public abstract class AbstractTransaction<AM extends Amount<? extends Number,AM>, D, TA, A> implements Transaction<AM, D, TA, A> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    @Column(columnDefinition = "DATE")
    private D date;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Embedded
    private AM amount;

    @Override
    public void setAmount(@NonNull AM amount) {
        if(amount.isZero())
            throw new IllegalArgumentException("Amount must be greater or less than zero");
        this.amount = amount;
    }
}

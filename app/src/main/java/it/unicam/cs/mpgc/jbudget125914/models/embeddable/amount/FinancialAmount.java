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

package it.unicam.cs.mpgc.jbudget125914.models.embeddable.amount;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.math.BigDecimal;

/**
 * This class represent a FinancialAmount
 */
@Getter
@Setter
@Embeddable
public class FinancialAmount implements Amount<BigDecimal, FinancialAmount>, Comparable<FinancialAmount> {

    @Column(precision = 14, scale = 2, nullable = false)
    private final BigDecimal amount;

    /**
     * Construct a new Amount
     * @param amount the new amount
     * @throws NullPointerException if {@code amount} is null
     */
    public FinancialAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * Financial Amount default constructor
     */
    public FinancialAmount() {
        amount = BigDecimal.ZERO;
    }

    @Override
    public String toString() {
        return amount.toString();
    }

    @Override
    public int signum() {
        return this.getAmount().signum();
    }

    @Override
    public boolean isZero() {
        return this.signum() == 0;
    }

    @Override
    public FinancialAmount add(FinancialAmount n) {
        if(n == null || n.getAmount() == null) {
            return new FinancialAmount(getAmount());
        }
        return new FinancialAmount(amount.add(n.getAmount()));
    }

    @Override
    public int compareTo(FinancialAmount o) {
        return this.getAmount().compareTo(o.getAmount());
    }
}

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

package it.unicam.cs.mpgc.jbudget125914.currency;

import org.jetbrains.annotations.NotNull;

/**
 * This class represent the money currency
 * @param amount the amount of this money object
 */
public record Euro(Double amount) implements Currency<Euro> {

    public Euro {
        if(amount == null)
            throw new NullPointerException("Amount cannot be null");
    }

    @Override
    public Double amount() {
        return this.amount;
    }

    @Override
    public String currency() {
        return "EUR";
    }

    @Override
    public boolean isNegative() {
        return this.amount < 0.0;
    }

    @Override
    public boolean isZero() {
        return this.amount == 0.0;
    }

    @Override
    public Euro sum(Euro that) {
        return new Euro(this.amount + that.amount);
    }

    @Override
    public Euro subtract(Euro currency) {
        return new Euro(this.amount - currency.amount);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(obj == this) return true;
        if(!(obj instanceof Euro that)) return false;

        return this.amount.doubleValue() == that.amount.doubleValue();
    }

    @Override
    public int compareTo(@NotNull Currency<Euro> o) {
        return this.amount.compareTo(o.amount().doubleValue());
    }
}

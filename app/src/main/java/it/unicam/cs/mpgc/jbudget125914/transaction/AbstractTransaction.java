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

package it.unicam.cs.mpgc.jbudget125914.transaction;

import it.unicam.cs.mpgc.jbudget125914.categories.DefaultCategory;
import it.unicam.cs.mpgc.jbudget125914.currency.Currency;

import java.time.LocalDate;
import java.util.Set;


/**
 * This abstract class provides a default implementation of a transaction that abstracts a general transaction.
 * @param <C> currency used for this transaction
 */
public abstract class AbstractTransaction<C extends Currency<C>> implements Transaction<C> {

    private final String description;
    private final C amount;
    private final LocalDate date;
    private final Set<DefaultCategory> categories;

    public AbstractTransaction(String description, C amount, LocalDate date, Set<DefaultCategory> categories) {
        if(date == null)
            throw new NullPointerException("date for the transaction cannot be null");
        if(amount.isZero())
            throw new IllegalArgumentException("value for the transaction cannot be zero");

        this.description = description;
        this.amount = amount;
        this.date = date;
        this.categories = categories;
    }

    @Override
    public String description() {
        return this.description;
    }

    @Override
    public C amount() {
        return this.amount;
    }

    @Override
    public LocalDate date() {
        return this.date;
    }

    @Override
    public Set<DefaultCategory> categories() {
        return this.categories;
    }

    @Override
    public String toString() {

        return "description: " + this.description + "\n" +
                "type: " + this.getType() + "\n" +
                "value: " + this.amount.amount() + "\n" +
                "date: " + this.date().toString() + "\n" +
                "categories: " + this.categories().toString() + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if(o == null)
            return false;
        if(!(o instanceof AbstractTransaction that))
            return false;
        if(this == o)
            return true;

        if(!this.description.equals(that.description()))
            return false;
        if(!this.amount.equals(that.amount()))
            return false;
        if(!this.date.equals(that.date()))
            return false;
        if(this.getType() != that.getType())
            return false;
        return (that.categories == null)? this.categories() == null : this.categories.containsAll(that.categories());
    }
}

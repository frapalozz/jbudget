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


package it.unicam.cs.mpgc.jbudget125914;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public final class MoneyTransaction implements Transaction<Money, Category> {

    private final String name;
    private final TransactionType type;
    private final Money value;
    private final Date date;
    private final Set<Category> categories;

    public MoneyTransaction(String name, TransactionType type, Money value, Date date, Set<Category> categories) {
        this.name = name;
        this.type = type;
        this.value = value;
        this.date = date;
        this.categories = categories;
    }

    public MoneyTransaction(String name, TransactionType type, Money value, Date date) {
        this(name, type, value, date, new HashSet<Category>());
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public MoneyTransaction setName(String name) {
        if(name == null)
            throw new NullPointerException("Name cannot be null");

        return new MoneyTransaction(name, this.type, this.value, this.date, this.categories);
    }

    @Override
    public Money getValue() {
        return this.value;
    }

    @Override
    public MoneyTransaction setValue(Money value) {
        if(value == null)
            throw new NullPointerException("Value cannot be null");

        return new MoneyTransaction(this.name, this.type, value, this.date, this.categories);
    }

    @Override
    public Date getDate() {
        return this.date;
    }

    @Override
    public MoneyTransaction setDate(Date date) {
        if(date == null)
            throw new NullPointerException("Date cannot be null");

        return new MoneyTransaction(this.name, this.type, this.value, date, this.categories);
    }

    @Override
    public Set<Category> getCategory() {
        return this.categories;
    }

    @Override
    public MoneyTransaction setCategory(Set<Category> categories) {
        if(categories == null)
            throw new NullPointerException("Category cannot be null");

        return new MoneyTransaction(this.name, this.type, this.value, date, categories);
    }

    @Override
    public TransactionType getTransactionType() {
        return this.type;
    }

    @Override
    public Transaction<Money, Category> setTransactionType(TransactionType type) {
        if(type == null)
            throw new NullPointerException("Type cannot be null");

        return new MoneyTransaction(this.name, type, this.value, this.date, this.categories);
    }

    @Override
    public String toString() {

        return  "name: " + this.name + "\n" +
                "type: " + this.type.toString() + "\n" +
                "value: " + this.value.toString() + "\n" +
                "date: " + this.date.toString() + "\n" +
                "categories: " + this.categories.toString() + "\n";
    }
}

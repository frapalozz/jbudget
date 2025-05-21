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

import org.joda.money.Money;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MoneyTransaction implements Transaction<Money, Category> {

    String name;
    TransactionType type;
    Money value;
    Date date;
    List<Category> categories;

    public MoneyTransaction(String name, TransactionType type, Money value, Date date, List<Category> categories) {
        this.name = name;
        this.type = type;
        this.value = value;
        this.date = date;
        this.categories = categories;
    }

    public MoneyTransaction(String name, TransactionType type, Money value, Date date) {
        this(name, type, value, date, new ArrayList<Category>());
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        if(name == null)
            throw new NullPointerException("Name cannot be null");

        this.name = name;
    }

    @Override
    public Money getValue() {
        return this.value;
    }

    @Override
    public void setValue(Money value) {
        if(value == null)
            throw new NullPointerException("Value cannot be null");
        this.value = value;
    }

    @Override
    public Date getDate() {
        return this.date;
    }

    @Override
    public void setDate(Date date) {
        if(date == null)
            throw new NullPointerException("Date cannot be null");
        this.date = date;
    }

    @Override
    public List<Category> getCategory() {
        return this.categories;
    }

    @Override
    public void setCategory(List<Category> categories) {
        if(categories == null)
            throw new NullPointerException("Category cannot be null");
        this.categories = categories;
    }

    @Override
    public TransactionType getTransactionType() {
        return this.type;
    }
}

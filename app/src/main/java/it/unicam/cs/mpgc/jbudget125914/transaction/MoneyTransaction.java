package it.unicam.cs.mpgc.jbudget125914.transaction;

import it.unicam.cs.mpgc.jbudget125914.categories.DefaultCategory;
import it.unicam.cs.mpgc.jbudget125914.currency.Money;

import java.time.LocalDate;
import java.util.Set;

public record MoneyTransaction(String name, Money value, LocalDate date, Set<DefaultCategory> categories) implements Transaction {

    public MoneyTransaction {
        if(name == null)
            throw new NullPointerException("name is null");
        if(date == null)
            throw new NullPointerException("date is null");
        if(value == null)
            throw new NullPointerException("value is null");
        if(categories == null)
            throw new NullPointerException("categories is null");
    }

    @Override
    public String toString() {

        return "name: " + this.name() + "\n" +
                "type: " + ( (this.value().isExpense()) ? "EXPENSE" : "INCOME") + "\n" +
                "value: " + this.value() + "\n" +
                "date: " + this.date().toString() + "\n" +
                "categories: " + this.categories().toString() + "\n";
    }
}

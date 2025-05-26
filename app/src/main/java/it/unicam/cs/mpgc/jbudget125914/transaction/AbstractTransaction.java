package it.unicam.cs.mpgc.jbudget125914.transaction;

import it.unicam.cs.mpgc.jbudget125914.categories.DefaultCategory;
import it.unicam.cs.mpgc.jbudget125914.currency.Currency;

import java.time.LocalDate;
import java.util.Set;

public abstract class AbstractTransaction<T extends Number, C extends Currency<T, C>> implements Transaction<C> {

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

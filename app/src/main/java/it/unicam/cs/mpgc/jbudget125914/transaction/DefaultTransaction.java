package it.unicam.cs.mpgc.jbudget125914.transaction;

import it.unicam.cs.mpgc.jbudget125914.categories.Category;
import it.unicam.cs.mpgc.jbudget125914.currency.UnitMeasure;

import java.util.Date;
import java.util.Set;

public record DefaultTransaction<T extends UnitMeasure<?>, C extends Category<?>>(
        String name,
        T value,
        Date date,
        Set<C> categories) implements Transaction<T, C> {

    public DefaultTransaction {
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

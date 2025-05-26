package it.unicam.cs.mpgc.jbudget125914.transaction;

import it.unicam.cs.mpgc.jbudget125914.categories.DefaultCategory;
import it.unicam.cs.mpgc.jbudget125914.currency.Money;

import java.time.LocalDate;
import java.util.Set;

public class Income extends AbstractTransaction<Double, Money> {
    public Income(String description, Money amount, LocalDate date, Set<DefaultCategory> categories) {
        super(description, amount, date, categories);
    }

    @Override
    public TransactionType getType() {
        return TransactionType.INCOME;
    }
}

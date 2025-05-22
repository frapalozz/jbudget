package it.unicam.cs.mpgc.jbudget125914.currency;

public interface UnitMeasure<U> {

    Number quantity();

    boolean isExpense();

    U unit();
}

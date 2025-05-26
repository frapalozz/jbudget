package it.unicam.cs.mpgc.jbudget125914.currency;

public interface Currency<T extends Number, C extends Currency<T, C>> extends Comparable<Currency<T, C>> {

    T amount();

    boolean isNegative();

    boolean isZero();

    C negate();

    /**
     * Return the sum of this amount + currency amount
     * @param currency the new currency to sum
     * @return the sum of this amount + currency amount
     */
    C sum(C currency);
}

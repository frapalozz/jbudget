package it.unicam.cs.mpgc.jbudget125914.models.repository.transaction;

import lombok.NonNull;

/**
 * Balance interface, gives you the ability to get the current balance
 * @param <A>
 * @param <F>
 */
public interface Balance<A,F> {

    /**
     * Return the amount of all period to the cutoff (inclusive)
     * @param filter filter
     * @return the current balance
     */
    A getBalance(@NonNull F filter);
}

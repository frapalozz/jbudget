package it.unicam.cs.mpgc.jbudget125914.service.filterService.filters;

public interface TransactionSelector<T> {

    /**
     * Return the transaction
     * @return the transaction
     */
    T getTransaction();

    /**
     * Set the new transaction
     * @param transaction the new transaction
     */
    void setTransaction(T transaction);
}

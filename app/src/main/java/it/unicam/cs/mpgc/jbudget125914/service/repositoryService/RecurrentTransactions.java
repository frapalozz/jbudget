package it.unicam.cs.mpgc.jbudget125914.service.repositoryService;

import lombok.NonNull;


public interface RecurrentTransactions<D, T> {

    /**
     * Generate the recurrence transactions
     * @param startDate start date of the recurrence
     * @param endDate end date of the recurrence
     * @param recurrence recurrence frequency
     * @param transaction transaction data
     */
    void generateRecurrentTransactions(@NonNull D startDate, @NonNull D endDate, Recurrence recurrence, T transaction);
}

package it.unicam.cs.mpgc.jbudget125914.models.services;

import java.time.temporal.Temporal;

public class ScheduleService<
        T, A, TA, N extends Number, D extends Temporal & Comparable<? super D>, AM, G
        > extends TransactionService<T,A,TA,N,D,AM,G> {

    public ScheduleService(Class<T> transactionClass) {
        super(transactionClass);
    }
}

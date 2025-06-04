package it.unicam.cs.mpgc.jbudget125914.model.entities.account;

import it.unicam.cs.mpgc.jbudget125914.model.entities.transaction.Transaction;
import it.unicam.cs.mpgc.jbudget125914.model.entities.transaction.details.AbstractDetail;

import java.util.List;

public interface Account<T extends AbstractDetail, D extends Transaction<T, D>> {

    T getInitial_details();

    String getName();

    String getDescription();

    Long getAccount_id();

    List<Transaction<T, D>> getTransactions();
}

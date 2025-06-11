package it.unicam.cs.mpgc.jbudget125914.model.account;

import it.unicam.cs.mpgc.jbudget125914.model.tag.Tag;
import it.unicam.cs.mpgc.jbudget125914.model.transaction.Transaction;
import it.unicam.cs.mpgc.jbudget125914.model.transaction.details.AbstractDetail;

import java.util.List;

public interface Account<T extends AbstractDetail, D extends Transaction<T, D, V>, V extends Tag<D, V>> {

    T getInitialDetails();

    T getCurrentDetails();

    String getName();

    String getDescription();

    Long getAccountId();

    List<D> getTransactions();
}

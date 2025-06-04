package it.unicam.cs.mpgc.jbudget125914.model.entities.category;

import it.unicam.cs.mpgc.jbudget125914.model.entities.transaction.Transaction;
import it.unicam.cs.mpgc.jbudget125914.model.entities.transaction.details.AbstractDetail;

import java.util.Set;

public interface Tag<T extends Transaction<? extends AbstractDetail, T>> {

    String getName();

    Tag<T> getParent();

    Set<Tag<T>> getChildrens();

    Set<T> getTransactions();
}

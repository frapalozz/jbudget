package it.unicam.cs.mpgc.jbudget125914.model.tag;

import it.unicam.cs.mpgc.jbudget125914.model.transaction.Transaction;
import it.unicam.cs.mpgc.jbudget125914.model.transaction.details.AbstractDetail;

import java.util.Set;

public interface Tag<T extends Transaction<? extends AbstractDetail, T, D>, D extends Tag<T, D>> {

    String getName();

    D getParent();

    Set<D> getChildrens();

    Set<T> getTransactions();

    Set<D> getTagsSubTree();
}

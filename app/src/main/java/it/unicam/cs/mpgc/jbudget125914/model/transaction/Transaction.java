package it.unicam.cs.mpgc.jbudget125914.model.transaction;

import it.unicam.cs.mpgc.jbudget125914.model.account.Account;
import it.unicam.cs.mpgc.jbudget125914.model.tag.Tag;
import it.unicam.cs.mpgc.jbudget125914.model.transaction.details.AbstractDetail;

import java.time.LocalDate;
import java.util.Set;

public interface Transaction<T extends AbstractDetail, D extends Transaction<T, D, V>, V extends Tag<D, V>> {

    /**
     * Return the transaction_id of this transaction
     * @return the transaction_id
     */
    Long getTransactionId();

    /**
     * Return the details of this transaction
     * @return the details of this transaction
     */
    T getDetails();

    /**
     * Return the date of this transaction
     * @return the date of this transaction
     */
    LocalDate getDate();

    /**
     * Return the description of this transaction
     * @return the description of this transaction
     */
    String getDescription();

    /**
     * Return the account associated to this transaction
     * @return the account associated to this transaction
     */
    Account<T, D, V> getAccount();

    /**
     * Return the tags associated to this transaction
     * @return the tags associated to this transaction
     */
    Set<V> getTags();
}

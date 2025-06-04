package it.unicam.cs.mpgc.jbudget125914.model.entities.transaction;

import it.unicam.cs.mpgc.jbudget125914.model.entities.account.Account;
import it.unicam.cs.mpgc.jbudget125914.model.entities.category.Tag;
import it.unicam.cs.mpgc.jbudget125914.model.entities.transaction.details.AbstractDetail;

import java.time.LocalDate;
import java.util.Set;

public interface Transaction<T extends AbstractDetail, D extends Transaction<T, D>> {

    /**
     * Return the transaction_id of this transaction
     * @return the transaction_id
     */
    Long getTransaction_id();

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
    Account<T, D> getAccount();

    /**
     * Return the tags associated to this transaction
     * @return the tags associated to this transaction
     */
    Set<Tag<D>> getTags();
}

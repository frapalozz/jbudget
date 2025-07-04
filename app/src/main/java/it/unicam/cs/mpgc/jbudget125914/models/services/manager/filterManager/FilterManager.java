package it.unicam.cs.mpgc.jbudget125914.models.services.manager.filterManager;

import it.unicam.cs.mpgc.jbudget125914.models.embeddable.amount.Amount;
import it.unicam.cs.mpgc.jbudget125914.models.entities.account.Account;
import it.unicam.cs.mpgc.jbudget125914.models.entities.category.Category;
import it.unicam.cs.mpgc.jbudget125914.models.entities.group.Group;
import it.unicam.cs.mpgc.jbudget125914.models.entities.tag.Tag;
import it.unicam.cs.mpgc.jbudget125914.models.entities.transaction.Transaction;

import java.time.temporal.Temporal;
import java.util.Set;

public interface FilterManager<
        T extends Transaction<AM,D,TA,A>,
        AM extends Amount<N, AM>,
        N extends Number,
        A extends Account<AM>,
        D extends Temporal & Comparable<? super D>,
        TA extends Tag<C>,
        C extends Category<TA>,
        G extends Group<TA,C,?,A>> {

    G getGroup();
    void setGroup(G group);

    D getStartDate();
    void setStartDate(D startDate);

    D getEndDate();
    void setEndDate(D endDate);

    Set<A> getAccounts();
    void setAccounts(Set<A> accounts);

    Set<TA> getTags();
    void setTags(Set<TA> tags);

    T getTransaction();
    void setTransaction(T transaction);
}

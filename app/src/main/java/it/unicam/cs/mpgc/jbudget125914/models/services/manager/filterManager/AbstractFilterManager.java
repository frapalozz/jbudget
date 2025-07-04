package it.unicam.cs.mpgc.jbudget125914.models.services.manager.filterManager;

import it.unicam.cs.mpgc.jbudget125914.models.embeddable.amount.Amount;
import it.unicam.cs.mpgc.jbudget125914.models.entities.account.Account;
import it.unicam.cs.mpgc.jbudget125914.models.entities.category.Category;
import it.unicam.cs.mpgc.jbudget125914.models.entities.group.Group;
import it.unicam.cs.mpgc.jbudget125914.models.entities.tag.Tag;
import it.unicam.cs.mpgc.jbudget125914.models.entities.transaction.Transaction;
import lombok.Getter;
import lombok.Setter;

import java.time.temporal.Temporal;
import java.util.Set;

@Getter
@Setter
public abstract class AbstractFilterManager<
        T extends Transaction<AM,D,TA,A>,
        AM extends Amount<N, AM>,
        N extends Number,
        A extends Account<AM>,
        D extends Temporal & Comparable<? super D>,
        TA extends Tag<C>,
        C extends Category<TA>,
        G extends Group<TA,C,?,A>> implements FilterManager<T,AM,N,A,D,TA,C,G> {

    D startDate;

    D endDate;

    private Set<A> accounts;

    private Set<TA> tags;

    private G group;

    private T transaction;
}

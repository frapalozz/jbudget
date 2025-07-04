package it.unicam.cs.mpgc.jbudget125914.models.services.manager.fetchManager;
import it.unicam.cs.mpgc.jbudget125914.models.embeddable.amount.Amount;
import it.unicam.cs.mpgc.jbudget125914.models.entities.account.Account;
import it.unicam.cs.mpgc.jbudget125914.models.entities.category.Category;
import it.unicam.cs.mpgc.jbudget125914.models.entities.group.Group;
import it.unicam.cs.mpgc.jbudget125914.models.entities.tag.Tag;
import it.unicam.cs.mpgc.jbudget125914.models.entities.transaction.Transaction;
import it.unicam.cs.mpgc.jbudget125914.models.services.manager.filterManager.AbstractFilterManager;
import it.unicam.cs.mpgc.jbudget125914.models.services.manager.generalManager.GeneralManager;
import lombok.Getter;
import lombok.Setter;

import java.time.temporal.Temporal;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public abstract class AbstractFetchManager<
        T extends Transaction<AM,D,TA,A>,
        S extends Transaction<AM,D,TA,A>,
        AM extends Amount<N, AM>,
        N extends Number,
        A extends Account<AM>,
        D extends Temporal & Comparable<? super D>,
        TA extends Tag<C>,
        C extends Category<TA>,
        G extends Group<TA,C,?,A>,
        GM extends GeneralManager<T,S,AM,N,A,D,TA,C,G, ?, ?, ?, ?, ?,?>,
        FM extends AbstractFilterManager<T,AM,N,A,D,TA,C,G,S>> implements FetchManager<T,S,AM,N,A,D,TA,C,G,GM,FM> {

    private AM balance;

    private List<T> transactions;

    private List<S> schedules;

    private List<Map<C, AM>> categoryBalance;

    private List<G> groups;
}

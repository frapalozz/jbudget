package it.unicam.cs.mpgc.jbudget125914.models.services.manager.fetchManager;

import it.unicam.cs.mpgc.jbudget125914.models.embeddable.amount.Amount;
import it.unicam.cs.mpgc.jbudget125914.models.entities.account.Account;
import it.unicam.cs.mpgc.jbudget125914.models.entities.category.Category;
import it.unicam.cs.mpgc.jbudget125914.models.entities.group.Group;
import it.unicam.cs.mpgc.jbudget125914.models.entities.tag.Tag;
import it.unicam.cs.mpgc.jbudget125914.models.entities.transaction.Transaction;
import it.unicam.cs.mpgc.jbudget125914.models.services.ScheduleService;
import it.unicam.cs.mpgc.jbudget125914.models.services.manager.Action;
import it.unicam.cs.mpgc.jbudget125914.models.services.manager.filterManager.FilterManager;
import it.unicam.cs.mpgc.jbudget125914.models.services.manager.generalManager.GeneralManager;

import java.time.temporal.Temporal;
import java.util.List;

public interface FetchManager<
        T extends Transaction<AM,D,TA,A>,
        S extends Transaction<AM,D,TA,A>,
        AM extends Amount<N, AM>,
        N extends Number,
        A extends Account<AM>,
        D extends Temporal & Comparable<? super D>,
        TA extends Tag<C>,
        C extends Category<TA>,
        G extends Group<TA,C,?,A>,
        GM extends GeneralManager<T,S,AM,N,A,D,TA,C,G, ?, ?, ?, ?,?,?>,
        FM extends FilterManager<T,AM,N,A,D,TA,C,G>
        > {

    void update(GM generalManager, FM filterManager, Action action);
}

package it.unicam.cs.mpgc.jbudget125914.models.services.manager;

import it.unicam.cs.mpgc.jbudget125914.models.embeddable.amount.Amount;
import it.unicam.cs.mpgc.jbudget125914.models.entities.account.Account;
import it.unicam.cs.mpgc.jbudget125914.models.entities.category.Category;
import it.unicam.cs.mpgc.jbudget125914.models.entities.group.Group;
import it.unicam.cs.mpgc.jbudget125914.models.entities.tag.Tag;
import it.unicam.cs.mpgc.jbudget125914.models.entities.transaction.Transaction;
import it.unicam.cs.mpgc.jbudget125914.models.services.manager.fetchManager.FetchManager;
import it.unicam.cs.mpgc.jbudget125914.models.services.manager.filterManager.FilterManager;
import it.unicam.cs.mpgc.jbudget125914.models.services.manager.generalManager.GeneralManager;
import javafx.beans.property.SimpleBooleanProperty;

import java.time.temporal.Temporal;


public interface ServiceManager<
        D extends Temporal & Comparable<? super D>,
        S extends Transaction<AM,D,TA,A>,
        C extends Category<TA>,
        A extends Account<AM>,
        TA extends Tag<C>,
        T extends Transaction<AM, D, TA, A>,
        AM extends Amount<N,AM>,
        N extends Number,
        G extends Group<TA, C,?,A>,
        GM extends GeneralManager<T,S,AM,N,A,D,TA,C,G,?,?,?,?,?,?>,
        FM extends FilterManager<T,AM,N,A,D,TA,C,G>,
        FTC extends FetchManager<T,S,AM,N,A,D,TA,C,G,GM,FM>> {

    void update();

    SimpleBooleanProperty getChanges();

    FM getFilterManager();
    FTC getFetchManager();
    GM getGeneralManager();
}

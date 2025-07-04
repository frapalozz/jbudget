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
import lombok.Getter;
import lombok.Setter;

import java.time.temporal.Temporal;

@Getter
@Setter
public abstract class AbstractServiceManager<
        T extends Transaction<AM, D, TA, A>,
        S extends Transaction<AM, D, TA, A>,
        A extends Account<AM>,
        TA extends Tag<C>,
        N extends Number,
        D extends Temporal & Comparable<? super D>,
        AM extends Amount<N ,AM>,
        C extends Category<TA>,
        G extends Group<TA, C, ?, A>,
        GM extends GeneralManager<T,S,AM,N,A,D,TA,C,G,?,?,?,?,?,?>,
        FM extends FilterManager<T,AM,N,A,D,TA,C,G>,
        FTC extends FetchManager<T,S,AM,N,A,D,TA,C,G,GM,FM>> implements ServiceManager<
            D, S, C, A, TA, T, AM, N, G,GM,FM,FTC> {

    private SimpleBooleanProperty changes = new SimpleBooleanProperty(false);

    private FTC fetchManager;

    private FM filterManager;

    private GM generalManager;

}

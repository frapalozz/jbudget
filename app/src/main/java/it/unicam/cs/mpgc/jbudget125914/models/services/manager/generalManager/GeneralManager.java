package it.unicam.cs.mpgc.jbudget125914.models.services.manager.generalManager;

import it.unicam.cs.mpgc.jbudget125914.models.embeddable.amount.Amount;
import it.unicam.cs.mpgc.jbudget125914.models.entities.account.Account;
import it.unicam.cs.mpgc.jbudget125914.models.entities.category.Category;
import it.unicam.cs.mpgc.jbudget125914.models.entities.group.Group;
import it.unicam.cs.mpgc.jbudget125914.models.entities.tag.Tag;
import it.unicam.cs.mpgc.jbudget125914.models.entities.transaction.Transaction;
import it.unicam.cs.mpgc.jbudget125914.models.services.*;

import java.time.temporal.Temporal;

public interface GeneralManager<
        T extends Transaction<AM,D,TA,A>,
        S extends Transaction<AM,D,TA,A>,
        AM extends Amount<N, AM>,
        N extends Number,
        A extends Account<AM>,
        D extends Temporal & Comparable<? super D>,
        TA extends Tag<C>,
        C extends Category<TA>,
        G extends Group<TA,C,?,A>,
        TS extends TransactionService<T, A, TA, N, D, AM, G>,
        AS extends AccountService<A>,
        CS extends CategoryService<AM, N, A, T, TA, C, D>,
        GS extends GroupService<G>,
        TAS extends TagService<TA, C>,
        SS extends ScheduleService<S, A, TA, N, D, AM, G>> {

    TS getTransactionService();
    void setTransactionService(TS ts);

    AS getAccountService();
    void setAccountService(AS as);

    CS getCategoryService();
    void setCategoryService(CS cs);

    GS getGroupService();
    void setGroupService(GS gs);

    TAS getTagService();
    void setTagService(TAS ts);

    SS getScheduleService();
    void setScheduleService(SS s);
}

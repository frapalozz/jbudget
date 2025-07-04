package it.unicam.cs.mpgc.jbudget125914.models.services.manager.generalManager;

import it.unicam.cs.mpgc.jbudget125914.models.embeddable.amount.Amount;
import it.unicam.cs.mpgc.jbudget125914.models.entities.account.Account;
import it.unicam.cs.mpgc.jbudget125914.models.entities.category.Category;
import it.unicam.cs.mpgc.jbudget125914.models.entities.group.Group;
import it.unicam.cs.mpgc.jbudget125914.models.entities.tag.Tag;
import it.unicam.cs.mpgc.jbudget125914.models.entities.transaction.Transaction;
import it.unicam.cs.mpgc.jbudget125914.models.services.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.time.temporal.Temporal;

@Getter
@Setter
public class AbstractGeneralManager<
        T extends Transaction<AM,D,TA,A>,
        AM extends Amount<N, AM>,
        N extends Number,
        A extends Account<AM>,
        D extends Temporal & Comparable<? super D>,
        TA extends Tag<C>,
        C extends Category<TA>,
        G extends Group<TA,C,?,A>,
        TS extends TransactionService<T, A, TA, N, D, AM, G>,
        AS extends AccountService<A, N, G>,
        CS extends CategoryService<AM, N, A, T, TA, C, D>,
        GS extends GroupService<G>,
        TAS extends TagService<TA, C>> implements GeneralManager<T,AM,N,A,D,TA,C,G,TS,AS,CS,GS,TAS> {

    private TS transactionService;

    private AS accountService;

    private CS categoryService;

    private GS groupService;

    private TAS tagService;
}

package it.unicam.cs.mpgc.jbudget125914.models.services.manager;

import it.unicam.cs.mpgc.jbudget125914.models.embeddable.amount.Amount;
import it.unicam.cs.mpgc.jbudget125914.models.entities.account.Account;
import it.unicam.cs.mpgc.jbudget125914.models.entities.category.Category;
import it.unicam.cs.mpgc.jbudget125914.models.entities.group.Group;
import it.unicam.cs.mpgc.jbudget125914.models.entities.tag.Tag;
import it.unicam.cs.mpgc.jbudget125914.models.entities.transaction.Transaction;
import it.unicam.cs.mpgc.jbudget125914.models.services.*;
import lombok.Getter;
import lombok.Setter;

import java.time.temporal.Temporal;

@Getter
public abstract class AbstractServiceManager<
        T extends Transaction<AM, D, TA, A>,
        A extends Account<AM>,
        TA extends Tag<C>,
        N extends Number,
        D extends Temporal & Comparable<? super D>,
        AM extends Amount<N ,AM>,
        C extends Category<TA>,
        G extends Group<TA, C, ?, A>> implements ServiceManager<
            TransactionService<T, A, TA, N, D, AM>,
            AccountService<A, N, AM>,
            CategoryService<AM, N, A, T, TA, C, D>,
            GroupService<G>,
            TagService<TA, C>> {

    private Long groupId;

    @Setter
    private TransactionService<T, A, TA, N, D, AM> transactionService;

    @Setter
    private AccountService<A, N, AM> accountService;

    @Setter
    private CategoryService<AM, N, A, T, TA, C, D> categoryService;

    @Setter
    private GroupService<G> groupService;

    @Setter
    private TagService<TA, C> tagService;

}

package it.unicam.cs.mpgc.jbudget125914.models.services.manager;

import it.unicam.cs.mpgc.jbudget125914.models.embeddable.amount.Amount;
import it.unicam.cs.mpgc.jbudget125914.models.entities.account.Account;
import it.unicam.cs.mpgc.jbudget125914.models.entities.category.Category;
import it.unicam.cs.mpgc.jbudget125914.models.entities.group.Group;
import it.unicam.cs.mpgc.jbudget125914.models.entities.tag.Tag;
import it.unicam.cs.mpgc.jbudget125914.models.entities.transaction.Transaction;
import it.unicam.cs.mpgc.jbudget125914.models.services.*;
import javafx.beans.property.SimpleIntegerProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.time.temporal.Temporal;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
            D, A, TA, T, C, AM, G> {

    @Setter
    private SimpleIntegerProperty changes = new SimpleIntegerProperty(0);

    @Setter
    private G group;

    @Setter
    private AM balance;

    @Setter
    private D startDate;

    @Setter
    private D endDate;

    @Setter
    private Set<A> accounts;

    @Setter
    private Set<TA> tags;

    @Setter
    private T transaction;

    @Setter
    private List<T> transactions;

    @Setter
    List<Map<C, AM>> categoryBalance;


    @Setter(value = AccessLevel.PACKAGE)
    private TransactionService<T, A, TA, N, D, AM, G> transactionService;

    @Setter(value = AccessLevel.PACKAGE)
    private AccountService<A, N, AM, G> accountService;

    @Setter(value = AccessLevel.PACKAGE)
    private CategoryService<AM, N, A, T, TA, C, D> categoryService;

    @Setter(value = AccessLevel.PACKAGE)
    private GroupService<G, A, TA> groupService;

    @Setter(value = AccessLevel.PACKAGE)
    private TagService<TA, C> tagService;

}

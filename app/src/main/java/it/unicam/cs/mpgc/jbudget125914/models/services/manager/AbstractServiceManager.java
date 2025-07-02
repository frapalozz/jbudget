package it.unicam.cs.mpgc.jbudget125914.models.services.manager;

import it.unicam.cs.mpgc.jbudget125914.models.embeddable.amount.Amount;
import it.unicam.cs.mpgc.jbudget125914.models.entities.account.Account;
import it.unicam.cs.mpgc.jbudget125914.models.entities.category.Category;
import it.unicam.cs.mpgc.jbudget125914.models.entities.group.Group;
import it.unicam.cs.mpgc.jbudget125914.models.entities.tag.Tag;
import it.unicam.cs.mpgc.jbudget125914.models.entities.transaction.Transaction;
import it.unicam.cs.mpgc.jbudget125914.models.services.*;
import javafx.beans.property.SimpleIntegerProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.temporal.Temporal;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
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
            GroupService<G, A, TA>,
            TagService<TA, C>,
            D, A, TA, T, C, AM> {

    private SimpleIntegerProperty changes = new SimpleIntegerProperty(0);

    private Long groupId;

    private D startDate;

    private D endDate;

    private Set<A> accounts;

    private Set<TA> tags;

    private List<T> transactions;

    List<Map<C, AM>> categoryBalance;


    private TransactionService<T, A, TA, N, D, AM> transactionService;


    private AccountService<A, N, AM> accountService;


    private CategoryService<AM, N, A, T, TA, C, D> categoryService;


    private GroupService<G, A, TA> groupService;


    private TagService<TA, C> tagService;

}

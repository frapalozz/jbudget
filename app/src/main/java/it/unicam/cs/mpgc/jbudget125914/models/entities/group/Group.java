package it.unicam.cs.mpgc.jbudget125914.models.entities.group;

import it.unicam.cs.mpgc.jbudget125914.models.entities.Nameable;
import it.unicam.cs.mpgc.jbudget125914.models.entities.account.Account;
import it.unicam.cs.mpgc.jbudget125914.models.entities.category.Categorizable;
import it.unicam.cs.mpgc.jbudget125914.models.entities.category.Category;
import it.unicam.cs.mpgc.jbudget125914.models.entities.tag.Tag;
import it.unicam.cs.mpgc.jbudget125914.models.entities.tag.Taggable;
import lombok.NonNull;

import java.io.Serializable;
import java.util.Set;

/**
 * This interface represent a Group
 * @param <I> ID type
 * @param <N> name type
 * @param <C> currency type
 * @param <T> tag type
 */
public interface Group<
        I extends Serializable,
        T extends Tag<I, N, CA>,
        CA extends Category<I, N, T>,
        A extends Account<I, N, ? extends Number>,
        N, C> extends Taggable<T>, Categorizable<CA>, Nameable<N> {

    /**
     * Return the Group ID
     * @return the Group ID
     */
    I getGroupId();

    /**
     * Return the Group currency
     * @return the Group currency
     */
    C getCurrency();

    /**
     * Set the new Group currency
     * @param currency the new Group currency
     */
    void setCurrency(@NonNull C currency);

    /**
     * Return the set of Account associated with this Group
     * @return the set of Account associated with this Group
     */
    Set<A> getAccounts();
}

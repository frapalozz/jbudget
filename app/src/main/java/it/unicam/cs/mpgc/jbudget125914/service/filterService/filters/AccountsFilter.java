package it.unicam.cs.mpgc.jbudget125914.service.filterService.filters;

import java.util.Set;

public interface AccountsFilter<A> {

    /**
     * Return the set of accounts
     * @return the set of accounts
     */
    Set<A> getAccounts();

    /**
     * Set the new set of accounts
     * @param accounts the new set of accounts
     */
    void setAccounts(Set<A> accounts);
}

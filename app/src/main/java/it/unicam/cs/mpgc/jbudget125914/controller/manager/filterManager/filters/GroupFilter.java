package it.unicam.cs.mpgc.jbudget125914.controller.manager.filterManager.filters;

public interface GroupFilter<G> {

    /**
     * Return the current Group
     * @return the current Group
     */
    G getGroup();

    /**
     * Set the new Group
     * @param group the new Group
     */
    void setGroup(G group);
}

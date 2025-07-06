package it.unicam.cs.mpgc.jbudget125914.controller.manager.fetchManager;

public interface UpdateGroup<GM, FM> {

    /**
     * Update the Group without causing an update to the data
     * @param generalManager generalManager
     * @param filterManager filterManager
     */
    void updateGroup(GM generalManager, FM filterManager);
}

package it.unicam.cs.mpgc.jbudget125914.controller.manager.filterManager.filters;

import java.util.Set;

public interface TagsFilter<TA> {

    /**
     * Return the set of tags
     * @return the set of tags
     */
    Set<TA> getTags();

    /**
     * Set the new set of tags
     * @param tags the new set of tags
     */
    void setTags(Set<TA> tags);
}

package it.unicam.cs.mpgc.jbudget125914.models.entities.tag;

import lombok.NonNull;

import java.util.Set;

public interface Taggable<T> {

    /**
     * Return the set of Tag associated
     * @return the set of Tag associated
     */
    Set<T> getTags();

    /**
     * Add a new Tag
     * @param tag the new Tag to add
     * @throws NullPointerException if {@code tag} is null
     */
    default void addTag(@NonNull T tag) {
        getTags().add(tag);
    }

    /**
     * Remove a Tag
     * @param tag the Tag to remove
     * @throws NullPointerException if {@code tag} is null
     */
    default void removeTag(@NonNull T tag) {
        getTags().remove(tag);
    }

    /**
     * Set the new tags
     * @param tags the new tags
     * @throws NullPointerException if {@code tags} is null
     */
    default void setTags(@NonNull Set<T> tags) {
        getTags().clear();
        getTags().addAll(tags);
    }
}

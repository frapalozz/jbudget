package it.unicam.cs.mpgc.jbudget125914.models.entities.category;

import lombok.NonNull;

import java.util.Set;

public interface Categorizable<T> {

    /**
     * Return the set of category associated
     * @return the set of category associated
     */
    Set<T> getCategories();

    /**
     * Add a new category
     * @param category the new category to add
     * @throws NullPointerException if {@code category} is null
     */
    default void addCategories(@NonNull T category) {
        getCategories().add(category);
    }

    /**
     * Remove a category
     * @param category the category to remove
     * @throws NullPointerException if {@code category} is null
     */
    default void removeCategory(@NonNull T category) {
        getCategories().remove(category);
    }

    /**
     * Set the new categories
     * @param categories the new categories
     * @throws NullPointerException if {@code categories} is null
     */
    default void setCategories(@NonNull Set<T> categories) {
        getCategories().clear();
        getCategories().addAll(categories);
    }
}

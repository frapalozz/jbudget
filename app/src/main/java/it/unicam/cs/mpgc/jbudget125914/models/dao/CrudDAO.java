/**
 * MIT License
 * Copyright (c) 2025 Francesco Palozzi
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package it.unicam.cs.mpgc.jbudget125914.models.dao;

import lombok.NonNull;

import java.util.List;

/**
 * This interface gives you the ability of the basics crud operations
 * @param <T> entity type
 */
public interface CrudDAO<T> {

    /**
     * Create a new entity
     * @param entity entity to create
     * @return the persisted entity
     * @throws NullPointerException if {@code entity} is null
     */
    T create(@NonNull T entity);

    /**
     * Create a new list of entities
     * @param entities list of entities
     */
    void create(@NonNull List<T> entities);

    /**
     * Update an entity
     * @param entity entity to update
     * @throws NullPointerException if {@code entity} is null
     */
    void update(@NonNull T entity);

    /**
     * Delete an entity
     * @param id id of the entity
     * @throws NullPointerException if {@code id} is null
     */
    <D> void delete(@NonNull D id);

    /**
     * Find an entity by is ID
     * @param id id to search for
     * @return the entity found or null if any entity are not found
     * @param <D> the type of ID
     */
    <D> T findById(@NonNull D id);

    /**
     * Return all the entities of {@code <T>} type from the database
     * @return all the entities of {@code <T>} type from the database
     */
    List<T> findAll();
}

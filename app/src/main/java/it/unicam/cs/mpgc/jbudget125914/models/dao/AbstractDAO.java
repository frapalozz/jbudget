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

import it.unicam.cs.mpgc.jbudget125914.models.dao.util.CriteriaQueryHelper;
import it.unicam.cs.mpgc.jbudget125914.models.dao.util.TransactionUtil;
import lombok.Getter;
import lombok.NonNull;

import java.util.List;

/**
 * This abstract class contains common CRUD operations
 * @param <T> entity type
 */
@Getter
public abstract class AbstractDAO<T> implements CrudDAO<T> {

    private final Class<T> entityClass;

    /**
     * Construct a new AbstractDAO
     * @param entityClass the entity for the DAO
     * @throws NullPointerException if {@code entityClass} is null
     */
    public AbstractDAO(@NonNull Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public T create(@NonNull T entity) {
        return TransactionUtil.executeInTransactionReturn(em -> em.merge(entity));
    }

    @Override
    public void create(@NonNull List<T> entities) {
        TransactionUtil.executeInTransaction(em ->
            entities.forEach(em::persist)
        );
    }

    @Override
    public T update(@NonNull T entity) {
        return TransactionUtil.executeInTransactionReturn(em -> em.merge(entity));
    }

    @Override
    public <D> void delete(@NonNull D id) {
        TransactionUtil.executeInTransaction(em -> em.remove(em.merge(findById(id))));
    }

    @Override
    public <D> T findById(@NonNull D id) {
        return TransactionUtil.executeInTransactionReturn(em -> em.find(entityClass, id));
    }

    @Override
    public List<T> findAll() {
        return TransactionUtil.executeInTransactionReturn(
                em -> new CriteriaQueryHelper<>(em, entityClass, entityClass).getResultList()
        );
    }
}

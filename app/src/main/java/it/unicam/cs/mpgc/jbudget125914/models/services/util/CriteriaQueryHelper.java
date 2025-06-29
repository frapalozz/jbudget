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

package it.unicam.cs.mpgc.jbudget125914.models.services.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import lombok.Getter;

import java.util.List;

/**
 * This class is a util class used to build a criteria query
 * @param <T> is the entity of the query
 */
public class CriteriaQueryHelper<T> {

    private final EntityManager em;
    @Getter
    private final CriteriaBuilder cb;
    @Getter
    private final CriteriaQuery<T> cq;
    @Getter
    private final Root<T> root;

    /**
     * CriteriaQueryHelper constructor
     * @param em EntityManager
     * @param entityClass entityClass
     * @throws NullPointerException if {@code em} is null
     * @throws NullPointerException if {@code entityClass} is null
     */
    public CriteriaQueryHelper(EntityManager em, Class<T> entityClass) {
        this.em = em;
        this.cb = em.getCriteriaBuilder();
        this.cq = cb.createQuery(entityClass);
        this.root = cq.from(entityClass);
    }

    /**
     * Return the query result
     * @return the query result
     */
    public List<T> getResultList() {
        return em.createQuery(cq).getResultList();
    }

    /**
     * Set the where predicates of the query
     * @param predicates to add
     * @return this CriteriaQueryHelper with the new predicates
     */
    public CriteriaQueryHelper<T> where(Predicate... predicates) {
        cq.where(predicates);
        return this;
    }

    /**
     * Set the order of the query
     * @param orders to add
     * @return this CriteriaQueryHelper with the new orders
     */
    public CriteriaQueryHelper<T> orderBy(Order... orders) {
        cq.orderBy(orders);
        return this;
    }
}

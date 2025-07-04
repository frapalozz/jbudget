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
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * This record is a util for the transaction operation
 */
public record TransactionUtil() {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("JBudget");

    /**
     * Execute operation with elements expected
     * @param operation operation to execute
     * @return the result of the operation
     * @param <R> the type of the returned element
     */
    public static <R> R executeInTransactionReturn(Function<EntityManager, R> operation) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        //noinspection TryFinallyCanBeTryWithResources
        try {
            tx.begin();
            R result = operation.apply(em);
            tx.commit();
            return result;
        } catch (Exception e) {
            if(tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException("Transaction failed", e);
        } finally {
            em.close();
        }
    }

    /**
     * Execute an operation
     * @param operation the operation to execute
     */
    public static void executeInTransaction(Consumer<EntityManager> operation) {
        executeInTransactionReturn(em -> {
            operation.accept(em);
            return null;
        });
    }
}

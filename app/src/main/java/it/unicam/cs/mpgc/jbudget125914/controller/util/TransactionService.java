package it.unicam.cs.mpgc.jbudget125914.controller.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.function.Consumer;

public record TransactionService() {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("JBudget");;

    public static EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }

    public static void executeInTransaction(Consumer<EntityManager> operation) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        //noinspection TryFinallyCanBeTryWithResources
        try {
            tx.begin();
            operation.accept(em);
            tx.commit();
        } catch (Exception e) {
            if(tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException("Transaction failed", e);
        } finally {
            em.close();
        }
    }

    public static void close() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}

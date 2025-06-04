package it.unicam.cs.mpgc.jbudget125914.model.dao;

import it.unicam.cs.mpgc.jbudget125914.model.dao.util.TransactionService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class GenericDao<T> {

    private final Class<T> entityClass;

    public GenericDao(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public void create(T entity) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JBudget");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();

        em.close();
        emf.close();
    }

    public void update(T entity) {
        TransactionService.executeInTransaction(em -> {

            em.getTransaction().begin();
            em.merge(entity);
            em.getTransaction().commit();

            em.close();
        });
    }

    public void delete(T entity) {
        TransactionService.executeInTransaction(em -> {

            em.getTransaction().begin();
            em.remove(em.merge(entity));
            em.getTransaction().commit();

            em.close();
        });
    }

    public List<T> findAll() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JBudget");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(entityClass);
        Root<T> root = cq.from(entityClass);
        cq.select(root);

        List<T> result = em.createQuery(cq).getResultList();

        em.getTransaction().commit();

        em.close();
        emf.close();

        return result;
    }

    public <D> T findById(D id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JBudget");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        T result = em.find(entityClass, id);

        em.getTransaction().commit();

        em.close();
        emf.close();

        return result;
    }
}

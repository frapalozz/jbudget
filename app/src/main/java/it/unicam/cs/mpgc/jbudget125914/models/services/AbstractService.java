package it.unicam.cs.mpgc.jbudget125914.models.services;

import it.unicam.cs.mpgc.jbudget125914.models.services.util.TransactionUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.Getter;

import java.util.List;

@Getter
public abstract class AbstractService<T> {

    private final Class<T> entityClass;

    AbstractService(Class<T> entityClass) {
        System.out.println("Qui");
        this.entityClass = entityClass;
    }

    public void create(T entity) {
        TransactionUtil.executeInTransaction(em -> em.persist(entity));
    }

    public void update(T entity) {
        TransactionUtil.executeInTransaction(em -> em.merge(entity));
    }

    public void delete(T entity) {
        TransactionUtil.executeInTransaction(em -> em.remove(em.merge(entity)));
    }

    public List<T> findAll() {
        return TransactionUtil.executeInTransactionReturn(em -> {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<T> cq = cb.createQuery(entityClass);
            Root<T> root = cq.from(entityClass);
            cq.select(root);

            return em.createQuery(cq).getResultList();
        });
    }

    public <D> T findById(D id) {
        return TransactionUtil.executeInTransactionReturn(em -> {
            return em.find(entityClass, id);
        });
    }
}

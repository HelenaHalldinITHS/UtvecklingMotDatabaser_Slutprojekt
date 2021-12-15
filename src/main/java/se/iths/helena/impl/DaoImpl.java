package se.iths.helena.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class DaoImpl {
    private EntityManager em;

    public DaoImpl() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        em = emf.createEntityManager();
    }

    public EntityManager getEntityManager() {
        return em;
    }

    public void closeEntityManager() {
        em.close();
    }
}

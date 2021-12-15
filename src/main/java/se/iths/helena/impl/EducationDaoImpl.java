package se.iths.helena.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import se.iths.helena.dao.EducationDao;
import se.iths.helena.entities.Education;

public class EducationDaoImpl implements EducationDao {
    EntityManagerFactory emf;
    EntityManager em;

    public EducationDaoImpl() {
        emf = Persistence.createEntityManagerFactory("jpa");
        em = emf.createEntityManager();
    }

    @Override
    public void add(Education education) {
        em.getTransaction().begin();
        em.persist(education);
        em.getTransaction().commit();
    }

    @Override
    public void update(Education education) {

    }

    @Override
    public void showInfo(Education education) {

    }

    @Override
    public void showAll() {

    }

    @Override
    public void delete(Education education) {

    }
}

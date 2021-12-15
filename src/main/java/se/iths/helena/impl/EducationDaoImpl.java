package se.iths.helena.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import se.iths.helena.dao.EducationDao;
import se.iths.helena.entities.Education;

import java.util.List;

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
        em.getTransaction().begin();
        em.merge(education);
        em.getTransaction().commit();
    }

    @Override
    public void showInfo(Education education) {
        education.print();
    }

    @Override
    public Education getById(int id) {
        return em.find(Education.class, id);
    }


    @Override
    public void showAll() {
       List<Education> result = em.createQuery("SELECT e FROM Education e", Education.class).getResultList();
       result.forEach(Education::print);
    }

    @Override
    public void delete(Education education) {
        em.getTransaction().begin();
        em.remove(education);
        em.getTransaction().commit();
    }

}

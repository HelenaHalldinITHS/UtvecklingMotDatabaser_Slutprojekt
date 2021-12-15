package se.iths.helena.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import se.iths.helena.dao.CourseDao;
import se.iths.helena.entities.Course;

public class CourseDaoImpl implements CourseDao {
    private EntityManagerFactory emf;
    private EntityManager em;

    public CourseDaoImpl(){
        emf = Persistence.createEntityManagerFactory("jpa");
        em = emf.createEntityManager();
    }


    @Override
    public void add(Course course) {
        em.getTransaction().begin();
        em.persist(course);
        em.getTransaction().commit();
    }

    @Override
    public void update(Course course) {
        em.getTransaction().begin();
        em.merge(course);
        em.getTransaction().commit();
    }

    @Override
    public void showInfo(Course course) {

    }

    @Override
    public Course getById(int id) {
        return null;
    }

    @Override
    public void showAll() {

    }

    @Override
    public void delete(Course course) {

    }
}

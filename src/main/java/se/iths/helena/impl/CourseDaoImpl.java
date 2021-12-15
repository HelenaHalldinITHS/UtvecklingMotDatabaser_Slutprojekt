package se.iths.helena.impl;

import jakarta.persistence.TypedQuery;
import se.iths.helena.dao.CourseDao;
import se.iths.helena.entities.Course;
import se.iths.helena.entities.Education;

import java.util.List;

public class CourseDaoImpl extends DaoImpl implements CourseDao {

    public CourseDaoImpl() {
        super();
    }

    @Override
    public void add(Course course) {
        getEntityManager().getTransaction().begin();
        getEntityManager().persist(course);
        getEntityManager().getTransaction().commit();
    }

    @Override
    public void update(Course course) {
        getEntityManager().getTransaction().begin();
        getEntityManager().merge(course);
        getEntityManager().getTransaction().commit();
    }

    @Override
    public void showInfo(Course course) {
        course.print();
    }

    @Override
    public Course getById(int id) {
        return getEntityManager().find(Course.class, id);
    }

    @Override
    public void showAll() {
        getEntityManager().createQuery("SELECT c FROM Course c", Course.class).getResultList().forEach(Course::print);
    }

    @Override
    public void delete(Course course) {
        getEntityManager().getTransaction().begin();
        getEntityManager().remove(course);
        getEntityManager().getTransaction().commit();
    }

    @Override
    public List<Course> getByEducation(Education education) {
        TypedQuery<Course> query = getEntityManager()
                .createQuery("SELECT c FROM Course c WHERE c.education = :education", Course.class);
        query.setParameter("education", education);
        return query.getResultList();
    }

}

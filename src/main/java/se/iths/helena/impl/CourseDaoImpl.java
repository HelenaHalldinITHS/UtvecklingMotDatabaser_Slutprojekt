package se.iths.helena.impl;

import jakarta.persistence.TypedQuery;
import se.iths.helena.dao.CourseDao;
import se.iths.helena.entities.Course;
import se.iths.helena.entities.Education;

import java.util.List;
import java.util.Optional;

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
        print(course);
    }

    @Override
    public Course getById(int id) {
        return getEntityManager().find(Course.class, id);
    }

    @Override
    public void showAll() {
        getEntityManager().createQuery("SELECT c FROM Course c", Course.class).getResultList().forEach(this::print);
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

    public void addCourseToEducation(Course course, Education education) {
        if (course.getEducation() != null)
            System.out.println("The course " + course.getName() + " is already connected to an other education (" + education.getName() + ")");
        else {
            course.setEducation(education);
            update(course);
        }
    }

    @Override
    public void removeCourseFromEducation(Course course) {
        if (course.getEducation() != null) {
            String education = course.getEducation().getName();
            course.setEducation(null);
            update(course);
            System.out.println("The course with id " + course.getId() + " is no longer part of the education " + education);
        } else
            System.out.println("The course with id " + course.getId() + " where not a part of anny education");
    }

    private void print(Course course) {
        String educationID = getEducationID(course);

        System.out.println("id:" + course.getId() +
                ", name:" + course.getName() + ", points:" +
                course.getPoints() + ", education_id:" + educationID);
    }

    private String getEducationID(Course course) {
        Optional<Education> education = Optional.ofNullable(course.getEducation());

        if (education.isEmpty())
            return " ";
        else
            return String.valueOf(education.get().getId());
    }

}

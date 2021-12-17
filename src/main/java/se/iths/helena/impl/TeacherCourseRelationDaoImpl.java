package se.iths.helena.impl;

import se.iths.helena.dao.TeacherCourseRelationDao;
import se.iths.helena.entities.*;

public class TeacherCourseRelationDaoImpl extends DaoImpl implements TeacherCourseRelationDao {

    public TeacherCourseRelationDaoImpl() {
        super();
    }

    @Override
    public void add(Teacher teacher, Course course) {
        TeacherCourseRelation relation = new TeacherCourseRelation(teacher, course);
        getEntityManager().getTransaction().begin();
        getEntityManager().persist(relation);
        getEntityManager().getTransaction().commit();
    }

    @Override
    public void add(TeacherCourseRelation relation) {
        getEntityManager().getTransaction().begin();
        getEntityManager().persist(relation);
        getEntityManager().getTransaction().commit();
    }

    @Override
    public void remove(TeacherCourseRelation relation) {
        getEntityManager().getTransaction().begin();
        getEntityManager().remove(relation);
        getEntityManager().getTransaction().commit();
    }

    @Override
    public TeacherCourseRelation get(Teacher teacher, Course course) {
        TeacherCourseRelationId id = new TeacherCourseRelationId(teacher, course);
        return getEntityManager().find(TeacherCourseRelation.class, id);
    }

    @Override
    public void showAll() {
        getEntityManager().createQuery("SELECT t FROM TeacherCourseRelation t", TeacherCourseRelation.class)
                .getResultList().forEach(this::print);
    }

    public void print(TeacherCourseRelation relation) {
        System.out.println("teacher_id:" + relation.getTeacher().getId() + "(" + relation.getTeacher().getFirstName() + " " + relation.getTeacher().getLastName() + ")" +
                ", course_id:" + relation.getCourse().getId() + "(" + relation.getCourse().getName() + ")");
    }
}

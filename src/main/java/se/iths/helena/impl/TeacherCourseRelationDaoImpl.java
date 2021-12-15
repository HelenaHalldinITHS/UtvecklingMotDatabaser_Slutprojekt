package se.iths.helena.impl;

import se.iths.helena.dao.TeacherCourseRelationDao;
import se.iths.helena.entities.Course;
import se.iths.helena.entities.Teacher;
import se.iths.helena.entities.TeacherCourseRelation;

public class TeacherCourseRelationDaoImpl extends DaoImpl implements TeacherCourseRelationDao {

    public TeacherCourseRelationDaoImpl(){
        super();
    }

    @Override
    public void add(Teacher teacher, Course course) {
        TeacherCourseRelation relation = new TeacherCourseRelation(teacher,course);
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
    public void showAll() {

    }
}

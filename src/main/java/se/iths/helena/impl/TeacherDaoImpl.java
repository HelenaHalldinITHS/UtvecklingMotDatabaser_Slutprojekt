package se.iths.helena.impl;

import se.iths.helena.dao.TeacherDao;
import se.iths.helena.entities.Teacher;

public class TeacherDaoImpl extends DaoImpl implements TeacherDao {

    public TeacherDaoImpl(){
        super();
    }

    @Override
    public void add(Teacher teacher) {
        getEntityManager().getTransaction().begin();
        getEntityManager().persist(teacher);
        getEntityManager().getTransaction().commit();
    }

    @Override
    public void update(Teacher teacher) {
        getEntityManager().getTransaction().begin();
        getEntityManager().merge(teacher);
        getEntityManager().getTransaction().commit();
    }

    @Override
    public void showInfo(Teacher teacher) {
        teacher.print();
    }

    @Override
    public Teacher getById(int id) {
        return getEntityManager().find(Teacher.class, id);
    }

    @Override
    public void showAll() {
        getEntityManager().createQuery("SELECT t FROM Teacher t", Teacher.class).getResultList().forEach(Teacher::print);
    }

    @Override
    public void delete(Teacher teacher) {
        getEntityManager().getTransaction().begin();
        getEntityManager().remove(teacher);
        getEntityManager().getTransaction().commit();
    }
}

package se.iths.helena.impl;

import se.iths.helena.dao.EducationDao;
import se.iths.helena.entities.Education;


public class EducationDaoImpl extends DaoImpl implements EducationDao {

    public EducationDaoImpl() {
        super();
    }

    @Override
    public void add(Education education) {
        getEntityManager().getTransaction().begin();
        getEntityManager().persist(education);
        getEntityManager().getTransaction().commit();
    }

    @Override
    public void update(Education education) {
        getEntityManager().getTransaction().begin();
        getEntityManager().merge(education);
        getEntityManager().getTransaction().commit();
    }

    @Override
    public void showInfo(Education education) {
        education.print();
    }

    @Override
    public Education getById(int id) {
        return getEntityManager().find(Education.class, id);
    }


    @Override
    public void showAll() {
        getEntityManager().createQuery("SELECT e FROM Education e", Education.class).getResultList().forEach(Education::print);
    }

    @Override
    public void delete(Education education) {
        getEntityManager().getTransaction().begin();
        getEntityManager().remove(education);
        getEntityManager().getTransaction().commit();
    }

}

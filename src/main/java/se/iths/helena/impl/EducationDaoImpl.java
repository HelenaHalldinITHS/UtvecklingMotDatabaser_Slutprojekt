package se.iths.helena.impl;

import se.iths.helena.dao.EducationDao;
import se.iths.helena.entities.Education;

import java.util.List;
import java.util.Optional;


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
        print(education);
    }

    @Override
    public Optional<Education> getById(int id) {
        return Optional.ofNullable(getEntityManager().find(Education.class, id));
    }

    @Override
    public List<Education> getAll() {
        return getEntityManager().createQuery("SELECT e FROM Education e", Education.class).getResultList();

    }


    @Override
    public void showAll() {
        getAll().forEach(this::print);
    }

    @Override
    public void delete(Education education) {
        getEntityManager().getTransaction().begin();
        getEntityManager().remove(education);
        getEntityManager().getTransaction().commit();
    }

    private void print(Education education){
        System.out.println("id:" + education.getId() +
                ", name:" + education.getName());
    }
}

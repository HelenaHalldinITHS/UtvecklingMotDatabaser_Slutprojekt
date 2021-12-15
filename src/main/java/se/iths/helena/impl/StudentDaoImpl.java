package se.iths.helena.impl;

import jakarta.persistence.TypedQuery;
import se.iths.helena.dao.StudentDao;
import se.iths.helena.entities.Education;
import se.iths.helena.entities.Student;

import java.util.List;

public class StudentDaoImpl extends DaoImpl implements StudentDao  {

    public StudentDaoImpl(){
        super();
    }

    @Override
    public void add(Student student) {
        getEntityManager().getTransaction().begin();
        getEntityManager().persist(student);
        getEntityManager().getTransaction().commit();
    }

    @Override
    public void update(Student student) {
        getEntityManager().getTransaction().begin();
        getEntityManager().merge(student);
        getEntityManager().getTransaction().commit();
    }

    @Override
    public void showInfo(Student student) {
        student.print();
    }

    @Override
    public Student getById(int id) {
        return getEntityManager().find(Student.class, id);
    }

    @Override
    public void showAll() {
        getEntityManager().createQuery("SELECT s FROM Student s", Student.class).getResultList().forEach(Student::print);
    }

    @Override
    public void delete(Student student) {
        getEntityManager().getTransaction().begin();
        getEntityManager().remove(student);
        getEntityManager().getTransaction().commit();
    }

    @Override
    public List<Student> getByEducation(Education education) {
        TypedQuery<Student> query = getEntityManager()
                .createQuery("SELECT s FROM Student s WHERE s.education = :education", Student.class);
        query.setParameter("education", education);
        return query.getResultList();
    }

    @Override
    public void registerToEducation(Student student, Education education) {
        if (student.getEducation() == null){
            student.setEducation(education);
            update(student);
        } else
            System.out.println("The student with id " + student.getId() + " is already registered to an education (" + student.getEducation().getName() + ")");
    }
}

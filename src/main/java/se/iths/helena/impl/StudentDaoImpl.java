package se.iths.helena.impl;

import jakarta.persistence.TypedQuery;
import se.iths.helena.dao.StudentDao;
import se.iths.helena.entities.Course;
import se.iths.helena.entities.Education;
import se.iths.helena.entities.Student;

import java.util.List;
import java.util.Optional;

public class StudentDaoImpl extends DaoImpl implements StudentDao {

    public StudentDaoImpl() {
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
        print(student);
    }

    @Override
    public Student getById(int id) {
        return getEntityManager().find(Student.class, id);
    }

    @Override
    public void showAll() {
        getAll().forEach(this::print);
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
    public void showByEducation(Education education) {
        getByEducation(education).forEach(this::print);
    }

    @Override
    public void registerToEducation(Student student, Education education) {
        if (student.getEducation() != null)
            System.out.println("The student with id " + student.getId() + " is already registered to an education (" + student.getEducation().getName() + ")");
        else {
            student.setEducation(education);
            update(student);
        }
    }

    @Override
    public void unregisterToEducation(Student student) {
        if (student.getEducation() != null) {
            String education = student.getEducation().getName();
            student.setEducation(null);
            update(student);
            System.out.println("The student with id " + student.getId() + " is no longer registered to the education " + education);
        } else
            System.out.println("The student with id " + student.getId() + " where not registered to anny education");
    }

    @Override
    public List<Student> getAll() {
       return getEntityManager().createQuery("SELECT s FROM Student s", Student.class).getResultList();
    }

    private void print(Student student) {
        String educationID = getEducationID(student);

        System.out.println("id:" + student.getId() +
                ", firstName:" + student.getFirstName() +
                ", lastName:" + student.getLastName() +
                ", education_id:" + educationID);
    }


    private String getEducationID(Student student) {
        Optional<Education> education = Optional.ofNullable(student.getEducation());

        if (education.isEmpty())
            return " ";
        else
            return String.valueOf(education.get().getId());
    }
}

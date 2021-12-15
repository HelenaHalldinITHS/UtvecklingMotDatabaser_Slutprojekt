package se.iths.helena;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import se.iths.helena.dao.EducationDao;
import se.iths.helena.entities.*;
import se.iths.helena.impl.EducationDaoImpl;

public class Application {

    public static void main(String[] args) {
        EducationDao educationDao = new EducationDaoImpl();
        Education education1 = new Education("Javautvecklare");
        Education education2 = new Education("UX-designer");
        //educationDao.add(education1);
        educationDao.add(education2);


        /* EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager em = emf.createEntityManager();

        Education education1 = new Education("Javautvecklare");
        Education education2 = new Education("UX-designer");
        Student student1 = new Student("Helena", "Halldin", education1);
        Student student2 = new Student("Sara", "Olsson", education1);
        Course course1 = new Course("Java", 60, education1);
        Course course2 = new Course("Sql", 35, education1);
        Teacher teacher1 = new Teacher("Eddie", "Neumann");
        Teacher teacher2 = new Teacher("Martin", "Test");
        TeacherCourseRelation tcr1 = new TeacherCourseRelation(teacher1, course1);
        TeacherCourseRelation tcr2 = new TeacherCourseRelation(teacher2, course1);
        TeacherCourseRelation tcr3 = new TeacherCourseRelation(teacher1, course2);
        TeacherCourseRelation tcr4 = new TeacherCourseRelation(teacher2, course2);

        em.getTransaction().begin();
        em.persist(education1);
        em.persist(education2);
        em.persist(student1);
        em.persist(student2);
        em.persist(course1);
        em.persist(course2);
        em.persist(teacher1);
        em.persist(teacher2);
        em.persist(tcr1);
        em.persist(tcr2);
        em.persist(tcr3);
        em.persist(tcr4);
        em.getTransaction().commit();
        em.close();

         */

    }
}

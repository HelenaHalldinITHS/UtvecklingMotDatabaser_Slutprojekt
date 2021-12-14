package se.iths.helena;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Application {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager em = emf.createEntityManager();

        Education education1 = new Education("Javautvecklare");
        Education education2 = new Education("UX-designer");
        Student student1 = new Student("Helena","Halldin",education1);
        Student student2 = new Student("Sara","Olsson", education1);
        Course course1 = new Course("Java",education1);
        Course course2 = new Course("Sql",education1);
        Teacher teacher1 = new Teacher("Eddie","Neumann");

        em.getTransaction().begin();
        em.persist(education1);
        em.persist(education2);
        em.persist(student1);
        em.persist(student2);
        em.persist(course1);
        em.persist(course2);
        em.persist(teacher1);
        em.getTransaction().commit();
        em.close();

    }
}

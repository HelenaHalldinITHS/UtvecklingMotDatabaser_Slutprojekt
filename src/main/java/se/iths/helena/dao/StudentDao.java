package se.iths.helena.dao;

import se.iths.helena.entities.Course;
import se.iths.helena.entities.Education;
import se.iths.helena.entities.Student;

import java.util.List;

public interface StudentDao {

    void add(Student student);

    void update(Student student);

    void showInfo(Student student);

    Student getById(int id);

    void showAll();

    void delete(Student student);

    List<Student> getByEducation(Education education);

    void registerToEducation(Student student, Education education);

}

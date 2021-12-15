package se.iths.helena.dao;

import se.iths.helena.entities.Student;

public interface StudentDao {

    void add(Student student);

    void update(Student student);

    void showInfo(Student student);

    Student getById(int id);

    void showAll();

    void delete(Student student);
}

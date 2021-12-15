package se.iths.helena.dao;

import se.iths.helena.entities.Teacher;

public interface TeacherDao {
    void add(Teacher teacher);

    void update(Teacher teacher);

    void showInfo(Teacher teacher);

    Teacher getById(int id);

    void showAll();

    void delete(Teacher teacher);
}

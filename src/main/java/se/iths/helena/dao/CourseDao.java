package se.iths.helena.dao;

import se.iths.helena.entities.Course;

public interface CourseDao {

    void add(Course course);

    void update(Course course);

    void showInfo(Course course);

    Course getById(int id);

    void showAll();

    void delete(Course course);
}

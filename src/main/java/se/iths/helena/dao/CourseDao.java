package se.iths.helena.dao;

import se.iths.helena.entities.Course;
import se.iths.helena.entities.Education;

import java.util.List;
import java.util.Optional;

public interface CourseDao {

    void add(Course course);

    void update(Course course);

    void showInfo(Course course);

    Optional<Course> getById(int id);

    void showAll();

    void delete(Course course);

    List<Course> getByEducation(Education education);

    void showByEducation(Education education);

    void addCourseToEducation(Course course, Education education);

    void removeCourseFromEducation(Course course);

    List<Course> getAll();
}

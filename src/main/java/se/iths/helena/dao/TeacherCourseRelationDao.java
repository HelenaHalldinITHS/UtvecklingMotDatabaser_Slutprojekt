package se.iths.helena.dao;

import se.iths.helena.entities.Course;
import se.iths.helena.entities.Teacher;

public interface TeacherCourseRelationDao {
    void add(Teacher teacher, Course course);

    void remove(Teacher teacher, Course course);

    void showAll();
}

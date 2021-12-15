package se.iths.helena.dao;

import se.iths.helena.entities.Course;
import se.iths.helena.entities.Teacher;
import se.iths.helena.entities.TeacherCourseRelation;

public interface TeacherCourseRelationDao {
    void add(Teacher teacher, Course course);

    void add(TeacherCourseRelation relation);

    void remove(Teacher teacher, Course course);

    void showAll();
}

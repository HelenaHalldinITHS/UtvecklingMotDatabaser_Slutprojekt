package se.iths.helena.entities;

import jakarta.persistence.*;

@Entity
public class TeacherCourseRelation {
    @EmbeddedId
    TeacherCourseRelationId id;

    public TeacherCourseRelation() {
    }

    public TeacherCourseRelation(Teacher teacher, Course course) {
        id = new TeacherCourseRelationId(teacher, course);
    }

    public Teacher getTeacher() {
        return id.getTeacher();
    }

    public void setTeacher(Teacher teacher) {
        this.id.setTeacher(teacher);
    }

    public Course getCourse() {
        return id.getCourse();
    }

    public void setCourse(Course course) {
        this.id.setCourse(course);
    }

}

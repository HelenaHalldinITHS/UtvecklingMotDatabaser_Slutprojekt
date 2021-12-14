package se.iths.helena.entities;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;

@Embeddable
public class TeacherCourseRelationId implements Serializable {
    @ManyToOne
    private Teacher teacher;

    @ManyToOne
    private Course course;

    public TeacherCourseRelationId() {
    }

    public TeacherCourseRelationId(Teacher teacher, Course course) {
        this.teacher = teacher;
        this.course = course;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TeacherCourseRelationId that = (TeacherCourseRelationId) o;

        if (teacher != null ? !teacher.equals(that.teacher) : that.teacher != null) return false;
        return course != null ? course.equals(that.course) : that.course == null;
    }

    @Override
    public int hashCode() {
        int result = teacher != null ? teacher.hashCode() : 0;
        result = 31 * result + (course != null ? course.hashCode() : 0);
        return result;
    }
}

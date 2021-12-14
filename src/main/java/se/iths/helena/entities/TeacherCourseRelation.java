package se.iths.helena.entities;

import jakarta.persistence.*;

@Entity
public class TeacherCourseRelation {
   @EmbeddedId
    TeacherCourseRelationId id;

   public TeacherCourseRelation(){}

    public TeacherCourseRelation(Teacher teacher, Course course){
        id = new TeacherCourseRelationId(teacher,course);
    }

}

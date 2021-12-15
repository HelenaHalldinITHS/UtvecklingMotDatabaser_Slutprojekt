package se.iths.helena.entities;

import jakarta.persistence.*;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private int points;

    @ManyToOne
    private Education education;

    public Course() {
    }

    public Course(String name, int points, Education education) {
        this.name = name;
        this.education = education;
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Education getEducation() {
        return education;
    }

    public void setEducation(Education education) {
        this.education = education;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void print(){
        System.out.println("id:" + id +
                ", name:" + name + ", points:" + points);
    }
}

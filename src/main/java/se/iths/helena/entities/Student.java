package se.iths.helena.entities;

import jakarta.persistence.*;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String firstName;
    private String lastName;

    @ManyToOne
    private Education education;

    public Student() {
    }

    public Student(String firstName, String lastName, Education education) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.education = education;
    }

    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Education getEducation() {
        return education;
    }

    public void setEducation(Education education) {
        this.education = education;
    }

    public void print(){
        System.out.println("id:" + id +
                ", firstName:" + firstName +
                ", lastName:" + lastName +
                ", education_id:" + education.getId() + "(" + education.getName() + ")");
    }
}

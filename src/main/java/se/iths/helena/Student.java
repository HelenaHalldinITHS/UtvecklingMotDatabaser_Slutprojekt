package se.iths.helena;

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

    public Student(){}

    public Student(String firstName, String lastName, Education education) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.education = education;
    }
}

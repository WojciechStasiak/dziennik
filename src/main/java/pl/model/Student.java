package pl.model;


import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Table (name = "students")
@Component
public class Student {

    @Id
    @Column(name = "id_student",nullable = false,unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_teacher", nullable = false)
    private Teacher teacher;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "idGroup",nullable = false)
    private Group group;

    public Student() {
    }

    public Student(Long id, String name, String surname, Teacher teacher, Group group) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.teacher = teacher;
        this.group = group;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", teacher=" + teacher +
                '}';
    }
}

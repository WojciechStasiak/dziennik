package pl.model;

import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Table(name = "grades")
@Component
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_grade", nullable = false, unique = true)
    private Long id_grade;

    @Column(nullable = false)
    private GradeEnum gradeEnum;

    @ManyToOne(cascade=CascadeType.MERGE)
    @JoinColumn(name = "id_student", nullable = true)
    private Student student;

    @Column(nullable = false)
    private String description;

    public Grade() {
    }

    public Grade(Student student) {
        this.student = student;
    }

    public Grade(GradeEnum gradeEnum, Student student, String description) {
        this.gradeEnum = gradeEnum;
        this.student = student;
        this.description = description;
    }

    public Grade(Long id_grade, GradeEnum gradeEnum, Student student, String description) {
        this.id_grade = id_grade;
        this.gradeEnum = gradeEnum;
        this.student = student;
        this.description = description;
    }

    public Long getId_grade() {
        return id_grade;
    }

    public void setId_grade(Long id_grade) {
        this.id_grade = id_grade;
    }

    public GradeEnum getGradeEnum() {
        return gradeEnum;
    }

    public void setGradeEnum(GradeEnum gradeEnum) {
        this.gradeEnum = gradeEnum;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}

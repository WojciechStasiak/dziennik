package pl.model;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "teachers")
@Component
public class Teacher {

    @Id
    @Column(name = "id_teacher", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_teacher;

    @Column(nullable = false)
    @NotEmpty
    @Size(min = 3)
    private String name;

    @Column(nullable = false)
    @NotEmpty
    @Size(min = 3)
    private String surname;

    @Column(unique = true)
    @Email
    @NotEmpty
    private String email;

    @Column(nullable = false)
    @NotEmpty
    @Size(min = 6)
    private String password;

    public Teacher() {
    }

    public Teacher(Long id_teacher, @NotEmpty @Size(min = 3) String name, @NotEmpty @Size(min = 3) String surname, @Email @NotEmpty String email, @NotEmpty @Size(min = 6) String password) {
        this.id_teacher = id_teacher;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
    }

    public Long getId_teacher() {
        return id_teacher;
    }

    public void setId_teacher(Long id_teacher) {
        this.id_teacher = id_teacher;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id_teacher=" + id_teacher +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

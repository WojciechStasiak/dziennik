package pl.model;

import org.springframework.stereotype.Component;
import javax.persistence.*;


@Entity
@Table(name = "`group`")
@Component
public class Group {

    @Id
    @Column(name = "idGroup",nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idGroup;

    @Column(name = "groupName")
    private String groupName;

    @ManyToOne
    @JoinColumn(name = "id_teacher",nullable = false)
    private Teacher teacher;

    public Group() {
    }

    public Group(Long idgroup, String groupName, Teacher teacher) {
        this.idGroup = idgroup;
        this.groupName = groupName;
        this.teacher = teacher;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Long getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(Long idGroup) {
        this.idGroup = idGroup;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public String toString() {
        return "Group{" +
                "idgroup=" + idGroup +
                ", groupName='" + groupName + '\'' +
                ", teacher=" + teacher +
                '}';
    }
}
package pl.repository;

import org.springframework.data.repository.CrudRepository;
import pl.model.Group;
import pl.model.Student;
import pl.model.Teacher;

import java.util.List;

public interface GroupRepository extends CrudRepository<Group, Long> {
    List<Group> findByTeacher(Teacher teacher);

    Group findByIdGroupAndTeacher(Long id_group, Teacher teacher);

    List<Student> findByTeacherAndIdGroup(Long id,Teacher teacher);
}

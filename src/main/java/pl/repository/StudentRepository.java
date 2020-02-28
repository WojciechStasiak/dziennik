package pl.repository;

import org.springframework.data.repository.CrudRepository;
import pl.model.Student;
import pl.model.Teacher;

import java.util.List;

public interface StudentRepository extends CrudRepository<Student, Long> {

    List<Student> findByNameAndTeacher(String name,Teacher teacher);

    List<Student> findByTeacher(Teacher teacher);

}

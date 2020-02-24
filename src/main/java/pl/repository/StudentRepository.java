package pl.repository;

import org.springframework.data.repository.CrudRepository;
import pl.model.Student;

import java.util.List;

public interface StudentRepository extends CrudRepository<Student, Long> {

    List<Student> findByName(String name);
}

package pl.repository;

import org.springframework.data.repository.CrudRepository;
import pl.model.Student;

import java.util.Optional;


public interface StudentRepository extends CrudRepository<Student, Long> {

    Optional<Student> findByName(String name);
}

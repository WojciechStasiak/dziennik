package pl.repository;

import org.springframework.data.repository.CrudRepository;
import pl.model.Teacher;

public interface TeacherRepository extends CrudRepository<Teacher, Long> {
    Teacher findByEmail(String email);

}

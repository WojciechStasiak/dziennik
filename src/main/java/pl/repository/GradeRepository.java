package pl.repository;

import org.springframework.data.repository.CrudRepository;
import pl.model.Grade;
import pl.model.Student;
import java.util.List;

public interface GradeRepository extends CrudRepository<Grade, Long> {
    List<Grade> findByStudent(Student student);

    void deleteByStudent(Long id);

    Grade getOne(Long id);
}
